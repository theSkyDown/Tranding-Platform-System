<!-- 

    商品交易页面

 -->
<template>
  <!-- 商品交易卡片 -->
  <el-card shadow="hover" v-loading="!this.$store.state.isLogin">
    <template #header>
      <div class="card-header">商品购买</div>
    </template>
    <div>
      <el-button
        type="success"
        style="margin-bottom: 5px"
        v-on:click="showAddDialog"
      >
        <el-icon><Plus /></el-icon>
        <span>发布商品</span>
      </el-button>

      <el-button style="margin-bottom: 5px" v-on:click="reloadGood">
        <el-icon><Refresh /></el-icon>
        <span>刷新</span>
      </el-button>
      <el-input
        v-model="keyword"
        placeholder="商品名称或商品描述"
        style="width: 200px; margin-left: 20px; margin-bottom: 5px"
      />
      <el-button
        type="primary"
        style="margin-bottom: 5px; margin-left: 5px"
        v-on:click="reloadGood"
      >
        <el-icon><Search /></el-icon>
        <span>搜索</span>
      </el-button>

      <el-table
        :data="goods"
        height="630"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        v-loading="tableLoading"
      >
        <el-table-column label="序号" type="index" width="80" />
        <el-table-column label="商品名称" prop="goodName" min-width="120" />
        <el-table-column
          label="商品价格"
          sortable
          prop="price"
          min-width="120"
        />
        <el-table-column label="商品类型" prop="categoryName" min-width="100" />
        <el-table-column
          label="商品描述"
          prop="description"
          show-overflow-tooltip
          min-width="120"
        >
        </el-table-column>

        <el-table-column label="支付方式" prop="payType" min-width="120">
          <template #default="scope">
            {{ scope.row.payType == 1 ? "现金支付" : "以物易物" }}
          </template>
        </el-table-column>

        <el-table-column label="发布者" prop="userUsername" min-width="120" />
        <el-table-column label="商品图片" prop="goodImg" min-width="120">
          <template #default="scope">
            <el-image
              :src="scope.row.goodImg"
              :preview-src-list="[scope.row.goodImg]"
              style="width: 80px; height: 80px"
              preview-teleported
            ></el-image>
          </template>
        </el-table-column>

        <el-table-column
          label="创建时间"
          sortable
          prop="createTime"
          min-width="160"
        />
        <el-table-column fixed="right" label="操作" width="100">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              v-on:click="buyGood(scope.row.goodId)"
            >
              <span>购买</span>
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

  <!-- 添加商品弹窗 -->
  <el-dialog
    v-model="addDialog"
    title="发布商品"
    width="35%"
    align-center
    draggable
  >
    <el-form label-position="top">
      <el-form-item label="商品名称" required>
        <el-input
          placeholder="请输入商品名称"
          maxlength="20"
          show-word-limit
          v-model="good.goodName"
        />
      </el-form-item>

      <el-form-item label="商品价格" required>
        <el-input
          placeholder="请输入商品的价格"
          maxlength="20"
          v-model="good.price"
          onkeyup="value=value.replace(/[^\d\.]/g,'')"
        />
      </el-form-item>

      <el-form-item label="商品类型" required>
        <el-select
          v-model="good.categoryId"
          filterable
          placeholder="请选择商品的类型"
        >
          <el-option
            v-for="item in categories"
            :key="item.categoryId"
            :label="item.categoryName"
            :value="item.categoryId"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="支付类型" required>
        <el-radio-group v-model="good.payType">
          <el-radio :label="1">现金支付</el-radio>
          <el-radio :label="2">以物易物</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="商品描述" required>
        <el-input
          placeholder="请输入商品的描述"
          type="textarea"
          :rows="3"
          show-word-limit
          resize="none"
          v-model="good.description"
        />
      </el-form-item>

      <el-form-item label="商品图片" required>
        <el-upload
          v-model:file-list="fileList"
          :action="this.$store.state.localhost + '/qiniu/good'"
          :limit="1"
          :on-exceed="fileExceed"
          :on-success="addFileSuccess"
          with-credentials
        >
          <el-button type="primary">点击上传</el-button>
          <template #tip>
            <div class="el-upload__tip">只能上传jpg/png文件,且不超过5000kb</div>
          </template>
        </el-upload>
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
</template>

<script>
import axios from "axios";
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      //商品列表
      goods: [],
      //分页参数
      page: 1, //当前页数
      num: 0, //从第几条开始算
      size: 10, //每页多少条数据
      total: 0, //总共多少条数据
      //表单加载
      tableLoading: false,
      //添加弹窗是否显示
      addDialog: false,
      //用于添加的good
      good: {
        goodName: "",
        price: "",
        description: "",
        payType: "",
        goodImg: "",
        categoryId: "",
      },
      //关键词
      keyword: "",
      //商品类型
      categories: [],
      //用于表单选择发布人信息
      users: [],
      //用于上传商品图片
      fileList: [],
    };
  },
  methods: {
    //获取所有角色的数据
    getAllGoods() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/good/all/trading?num=" +
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
          that.goods = res.data.data;
        }
      });
    },
    //刷新
    reloadGood() {
      this.tableLoading = true;
      //查询所有所有商品
      this.getAllGoods();
      //统计数量
      this.getTotal();
      setTimeout(() => {
        this.tableLoading = false;
      }, 300);
    },
    //每页显示的数据量发生改变
    sizeChange(pageSize) {
      // 更新每页大小
      this.size = pageSize;
      //   刷新页面
      this.reloadGood();
    },
    //当前页数发生改变
    numChange(page) {
      // 更新当前页数
      this.page = page;
      // 更新num用于发请求
      this.num = (page - 1) * this.size;
      //刷新页面
      this.reloadGood();
    },
    //打开添加弹窗
    showAddDialog() {
      this.addDialog = true;
      let that = this;
      //获取商品类型
      if (this.categories.length == 0) {
        axios({
          url:
            this.$store.state.localhost +
            "/category/all?num=0&size=100&keyword=",
          method: "get",
          withCredentials: true,
        }).then(function (res) {
          that.categories = res.data.data;
        });
      }
      //上传列表清空
      this.fileList = [];
    },
    //发送添加请求
    add() {
      let that = this;
      axios({
        url: this.$store.state.localhost + "/good/add",
        method: "post",
        withCredentials: true,
        headers: {
          "Content-Type": "application/json",
        },
        data: JSON.stringify(this.good),
      }).then(function (res) {
        // 打印信息
        ElMessage({
          message: res.data.message,
          type: res.data.status ? "success" : "error",
        });
        if (res.data.status) {
          //清空表单
          that.good = {};
          //关闭添加弹窗
          that.addDialog = false;
          //刷新表格
          that.reloadGood();
        }
      });
    },
    //获取统计角色的数据
    getTotal() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/good/total/trading?keyword=" +
          this.keyword,
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          that.total = res.data.data;
        }
      });
    },
    //文件超出限制触发
    fileExceed() {
      ElMessage({
        message: "只能上传一张图片",
        type: "warning",
      });
    },
    //添加上传成功后触发
    addFileSuccess(res) {
      ElMessage({
        message: res.message,
        type: res.status ? "success" : "error",
      });
      if (res.status) {
        this.good.goodImg = res.data;
      }
    },
    //购买商品
    buyGood(goodId) {
      let that = this;

      this.$confirm("是否要购买该商品?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios({
            url: this.$store.state.localhost + "/good/buy?goodId=" + goodId,
            method: "post",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
            that.reloadGood();
          });
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
  mounted() {
    this.reloadGood();
  },
};
</script>
<style>
/* 商品描述详细信息查看 */
.el-table .el-popper {
  width: 200px !important;
  max-height: 100px !important;
  overflow: scroll !important;
}
</style>
