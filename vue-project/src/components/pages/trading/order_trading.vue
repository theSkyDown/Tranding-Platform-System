<!-- 

    订单管理页面

 -->

<template>
  <!-- 订单管理卡片 -->
  <el-card shadow="hover" v-loading="!this.$store.state.isLogin">
    <template #header>
      <div class="card-header">订单管理</div>
    </template>
    <div>
      <el-button style="margin-bottom: 5px" v-on:click="reloadOrder">
        <el-icon><Refresh /></el-icon>
        <span>刷新</span>
      </el-button>
      <el-input
        v-model="keyword"
        placeholder="用户名"
        style="width: 200px; margin-left: 20px; margin-bottom: 5px"
      />
      <el-button
        type="primary"
        style="margin-bottom: 5px; margin-left: 5px"
        v-on:click="reloadOrder"
      >
        <el-icon><Search /></el-icon>
        <span>搜索</span>
      </el-button>

      <el-table
        :data="orders"
        height="630"
        style="width: 100%"
        v-loading="tableLoading"
      >
        <el-table-column label="序号" type="index" width="80" />
        <el-table-column label="商品名称" prop="goodName" min-width="120" />
        <el-table-column label="出售者" prop="saleUsername" min-width="120" />

        <el-table-column
          label="创建时间"
          sortable
          prop="createTime"
          min-width="160"
        />

        <el-table-column label="状态" prop="status" min-width="120">
          <template #default="scope">
            <span>{{ scope.row.status == 1 ? "完成交易" : "未完成交易" }}</span>
          </template>
        </el-table-column>

        <el-table-column label="评分" prop="rate" min-width="160">
          <template #default="scope">
            <el-rate
              v-model="scope.row.rate"
              disabled
              show-score
              score-template="{value} 分"
            />
          </template>
        </el-table-column>

        <el-table-column fixed="right" label="操作" width="200">
          <template #default="scope">
            <el-button
              :disabled="scope.row.status == 1"
              size="small"
              type="success"
              v-on:click="
                showRateDialog = true;
                orderId = scope.row.orderId;
              "
            >
              <span>完成订单</span>
            </el-button>
            <el-button
              :disabled="scope.row.status == 1"
              size="small"
              type="danger"
              v-on:click="cancelOrder(scope.row.orderId)"
            >
              <span>取消订单</span>
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

  <el-dialog
    v-model="showRateDialog"
    title="评分"
    width="25%"
    align-center
    draggable
  >
    <span class="demonstration">请对这次交易进行评分</span>
    <el-rate v-model="rate" />

    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button
          v-on:click="
            showRateDialog = false;
            reloadOrder();
          "
          >取消</el-button
        >
        <el-button type="primary" v-on:click="completeOrder">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import axios from "axios";
import { ElMessage, ElMessageBox } from "element-plus";
export default {
  data() {
    return {
      //订单列表
      orders: [],
      //分页参数
      page: 1, //当前页数
      num: 0, //从第几条开始算
      size: 10, //每页多少条数据
      total: 0, //总共多少条数据
      //表单加载
      tableLoading: false,
      //关键词
      keyword: "",
      //评分弹窗
      showRateDialog: false,
      //评分
      rate: 0.0,
      //用于订单评分的订单id
      orderId: "",
    };
  },
  methods: {
    //获取所有订单的数据
    getAllOrder() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/order/all/trading?num=" +
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
          that.orders = res.data.data;
        }
      });
    },
    //刷新
    reloadOrder() {
      this.tableLoading = true;
      //查询所有角色
      this.getAllOrder();
      //统计数量
      this.getTotal();
      setTimeout(() => {
        this.tableLoading = false;
      }, 300);
    },
    //取消该订单
    cancelOrder(id) {
      let that = this;
      this.$confirm("此操作将永久删除该订单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios({
            url: this.$store.state.localhost + "/order/cancel?orderId=" + id,
            method: "delete",
            withCredentials: true,
          }).then(function (res) {
            ElMessage({
              message: res.data.message,
              type: res.data.status ? "success" : "error",
            });
            that.reloadOrder();
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
      this.reloadOrder();
    },
    //当前页数发生改变
    numChange(page) {
      // 更新当前页数
      this.page = page;
      // 更新num用于发请求
      this.num = (page - 1) * this.size;
      //刷新页面
      this.reloadOrder();
    },
    //获取统计角色的数据
    getTotal() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/order/total/trading?keyword=" +
          this.keyword,
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          that.total = res.data.data;
        }
      });
    },
    //完成订单操作
    completeOrder() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/order/complete?orderId=" +
          this.orderId +
          "&rate=" +
          1.0 * this.rate,
        method: "put",
        withCredentials: true,
      }).then(function (res) {
        ElMessage({
          message: res.data.message,
          type: res.data.status ? "success" : "error",
        });
        that.showRateDialog = false;
        that.reloadOrder();
      });
    },
  },
  mounted() {
    this.reloadOrder();
  },
};
</script>
