<!-- 

    分类管理页面

 -->
<template>
  <!-- 分类管理卡片 -->
  <el-card shadow="hover" v-loading="!this.$store.state.isLogin">
    <template #header>
      <div class="card-header">分类管理</div>
    </template>
    <div>
      <el-button
        type="primary"
        style="margin-bottom: 5px"
        v-on:click="addDialog = true"
      >
        <el-icon><Plus /></el-icon>
        <span>添加</span>
      </el-button>
      <el-button
        style="margin-bottom: 5px"
        type="danger"
        v-on:click="deleteCategoryByBatchIds"
      >
        <el-icon><Delete /></el-icon>
        <span>批量删除</span>
      </el-button>
      <el-button style="margin-bottom: 5px" v-on:click="reloadCategory">
        <el-icon><Refresh /></el-icon>
        <span>刷新</span>
      </el-button>
      <el-input
        v-model="keyword"
        placeholder="分类的名称"
        style="width: 200px; margin-left: 20px; margin-bottom: 5px"
      />
      <el-button
        type="primary"
        style="margin-bottom: 5px; margin-left: 5px"
        v-on:click="reloadCategory"
      >
        <el-icon><Search /></el-icon>
        <span>搜索</span>
      </el-button>

      <el-table
        :data="categories"
        height="630"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        v-loading="tableLoading"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column label="序号" type="index" width="80" />
        <el-table-column label="分类名" prop="categoryName" min-width="120" />
        <el-table-column
          label="创建时间"
          sortable
          prop="createTime"
          min-width="160"
        />

        <el-table-column fixed="right" label="操作" width="170">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              v-on:click="showUpdateDialog(scope.row)"
            >
              <el-icon><EditPen /></el-icon>
              <span>修改</span>
            </el-button>
            <el-button
              size="small"
              type="danger"
              v-on:click="deleteCategory(scope.row.categoryId)"
            >
              <el-icon><Delete /></el-icon>
              <span>删除</span>
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="demo-pagination-block">
        <el-pagination
          :small="small"
          layout="prev, pager, next, jumper, sizes , total"
          :total="total"
          :current-page="page"
          :page-size="size"
          @size-change="sizeChange"
          @current-change="numChange"
        />
      </div>
    </div>
  </el-card>

  <!-- 添加分类弹窗 -->
  <el-dialog
    v-model="addDialog"
    title="添加分类"
    width="25%"
    align-center
    draggable
  >
    <el-form label-position="top">
      <el-form-item label="分类名" required>
        <el-input
          placeholder="请输入分类名"
          maxlength="10"
          show-word-limit
          v-model="category.categoryName"
        />
      </el-form-item>
    </el-form>
    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button v-on:click="addDialog = false">取消</el-button>
        <el-button type="primary" v-on:click="add">添加</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 修改弹窗 -->
  <el-dialog
    v-model="updateDialog"
    title="修改分类信息"
    width="25%"
    align-center
    draggable
  >
    <el-form label-position="top">
      <el-form-item label="分类名" required>
        <el-input
          placeholder="请输入分类名"
          maxlength="10"
          show-word-limit
          v-model="updateCategory.categoryName"
        />
      </el-form-item>
    </el-form>
    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button
          v-on:click="
            updateDialog = false;
            this.reloadCategory();
          "
          >取消</el-button
        >
        <el-button type="primary" v-on:click="update">修改</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import axios from "axios";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      //分类列表
      categories: [],
      //分页参数
      page: 1, //当前页数
      num: 0, //从第几条开始算
      size: 10, //每页多少条数据
      total: 0, //总共多少条数据
      // 多选选中的id
      selectId: [],
      //表单加载
      tableLoading: false,
      //添加弹窗是否显示
      addDialog: false,
      //用于添加的category
      category: {
        categoryName: "",
      },
      //修改弹窗是否显示
      updateDialog: false,
      //用于修改的category
      updateCategory: {},
      //关键词
      keyword: "",
    };
  },
  methods: {
    //获取所有角色的数据
    getAllCategory() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/category/all?num=" +
          this.num +
          "&size=" +
          this.size +
          "&keyword=" +
          this.keyword,
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        // 打印信息
        ElMessage({
          message: res.data.status ? "获取成功" : res.data.message,
          type: res.data.status ? "success" : "error",
        });
        if (res.data.status) {
          that.categories = res.data.data;
        }
      });
    },
    //批量删除
    deleteCategoryByBatchIds() {
      let that = this;
      //拼接字符串，用于请求的参数
      this.$confirm("此操作将永久删除这些分类信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let ids = "";
          for (let i = 0; i < this.selectId.length; i++) {
            ids = ids + this.selectId[i] + ",";
          }
          ids = ids.substring(0, ids.lastIndexOf(","));
          axios({
            url:
              this.$store.state.localhost + "/category/delete/ids?ids=" + ids,
            method: "delete",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
            that.reloadCategory();
          });
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //刷新
    reloadCategory() {
      this.tableLoading = true;
      //查询所有角色
      this.getAllCategory();
      //统计数量
      this.getTotal();
      setTimeout(() => {
        this.tableLoading = false;
      }, 300);
    },
    //多选栏，选中触发
    handleSelectionChange(val) {
      let temp = [];
      for (let i = 0; i < val.length; i++) {
        temp[i] = val[i].categoryId;
      }
      this.selectId = temp;
    },
    //显示修改弹窗
    showUpdateDialog(category) {
      this.updateDialog = true;
      //信息回显
      this.updateCategory = category;
    },
    //删除指定分类
    deleteCategory(id) {
      let that = this;
      this.$confirm("此操作将永久删除该分类信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios({
            url:
              this.$store.state.localhost + "/category/delete?categoryId=" + id,
            method: "delete",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
            that.reloadCategory();
          });
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //每页显示的数据量发生改变
    sizeChange(pageSize) {
      // 更新每页大小
      this.size = pageSize;
      //   刷新页面
      this.reloadCategory();
    },
    //当前页数发生改变
    numChange(page) {
      // 更新当前页数
      this.page = page;
      // 更新num用于发请求
      this.num = (page - 1) * this.size;
      //刷新页面
      this.reloadCategory();
    },
    //发送添加请求
    add() {
      let that = this;
      axios({
        url: this.$store.state.localhost + "/category/add",
        method: "post",
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(this.category),
      }).then(function (res) {
        // 打印信息
        ElMessage({
          message: res.data.message,
          type: res.data.status ? "success" : "error",
        });
        if (res.data.status) {
          //清空表单
          that.category = {};
          //关闭添加弹窗
          that.addDialog = false;
          //刷新表格
          that.reloadCategory();
        }
      });
    },
    //发送修改请求
    update() {
      let that = this;
      axios({
        url: this.$store.state.localhost + "/category/update",
        method: "put",
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(this.updateCategory),
      }).then(function (res) {
        ElMessage({
          message: res.data.message,
          type: res.data.status ? "success" : "error",
        });
        //如果修改成功
        if (res.data.status) {
          //清空表单
          that.updateCategory = {};
          //关闭添加弹窗
          that.updateDialog = false;
          //刷新表格
          that.reloadCategory();
        }
      });
    },
    //获取统计角色的数据
    getTotal() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/category/total?keyword=" +
          this.keyword,
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          that.total = res.data.data;
        }
      });
    },
  },
  mounted() {
    this.reloadCategory();
  },
};
</script>
