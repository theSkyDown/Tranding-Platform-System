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
      <el-button
        style="margin-bottom: 5px"
        type="danger"
        v-on:click="deleteOrdersByBatchIds"
      >
        <el-icon><Delete /></el-icon>
        <span>批量删除</span>
      </el-button>
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
        @selection-change="handleSelectionChange"
        v-loading="tableLoading"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column label="序号" type="index" width="80" />
        <el-table-column label="商品名称" prop="goodName" min-width="120">
          <template #default="scope">
            <span
              v-on:click="showGoodDetailInfo(scope.row.goodId)"
              class="goodName"
              >{{ scope.row.goodName }}</span
            >
          </template>
        </el-table-column>
        <el-table-column label="出售者" prop="saleUsername" min-width="120">
          <template #default="scope">
            <span
              class="saleUsername"
              v-on:click="showSaleDetaileInfo(scope.row.saleUserId)"
              >{{ scope.row.saleUsername }}</span
            >
          </template>
        </el-table-column>
        <el-table-column label="购买者" prop="buyUsername" min-width="120" />
        <el-table-column label="状态" prop="status" min-width="120">
          <template #default="scope">
            <span>{{ scope.row.status == 1 ? "完成交易" : "未完成交易" }}</span>
          </template>
        </el-table-column>

        <el-table-column label="评分" prop="rate" min-width="170">
          <template #default="scope">
            <el-rate
              v-model="scope.row.rate"
              disabled
              show-score
              score-template="{value} 分"
            />
          </template>
        </el-table-column>

        <el-table-column
          label="创建时间"
          sortable
          prop="createTime"
          min-width="160"
        />

        <el-table-column
          label="修改时间"
          sortable
          prop="updateTime"
          min-width="160"
        />

        <el-table-column fixed="right" label="操作" width="100">
          <template #default="scope">
            <el-button
              size="small"
              type="danger"
              v-on:click="deleteOrder(scope.row.orderId)"
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

  <el-dialog
    title="商品详细信息"
    width="25%"
    align-center
    draggable
    v-model="goodDetailDialog"
  >
    <div class="good-detail-content">
      <div>商品名称：{{ this.good.goodName }}</div>
      <div>商品价格：{{ this.good.price }}</div>
      <div>商品类型：{{ this.good.categoryName }}</div>
      <div>商品描述：{{ this.good.description }}</div>
      <div>
        支付方式：{{ this.good.payType == 1 ? "现金支付" : "以物易物" }}
      </div>
      <div>发布人：{{ this.good.userUsername }}</div>
      <div>
        <span class="good-img-title">商品图片：</span>
        <el-image
          class="good-img"
          :src="this.good.goodImg"
          :preview-src-list="[this.good.goodImg]"
          style="width: 80px; height: 80px"
          preview-teleported
        ></el-image>
      </div>
      <div>发布时间：{{ this.good.createTime }}</div>
    </div>
    <!-- 底部按钮 -->
    <template #footer>
      <el-button type="primary" v-on:click="goodDetailDialog = false"
        >确认</el-button
      >
    </template>
  </el-dialog>

  <!-- 卖家详细信息 -->
  <el-dialog
    title="卖家详细信息"
    width="25%"
    align-center
    draggable
    v-model="userDetailDialog"
  >
    <div class="good-detail-content">
      <div>用户名：{{ this.saleUser.username }}</div>
      <div>联系方式：{{ this.saleUser.phone }}</div>
      <div>性别：{{ this.saleUser.genderName }}</div>
      <div>角色：{{ this.saleUser.roleName }}</div>
      <div>
        <span class="good-img-title">头像：</span>
        <el-image
          class="good-img"
          :src="this.saleUser.avatar"
          :preview-src-list="[this.saleUser.avatar]"
          style="width: 80px; height: 80px"
          preview-teleported
        ></el-image>
      </div>
    </div>
    <!-- 底部按钮 -->
    <template #footer>
      <el-button type="primary" v-on:click="userDetailDialog = false"
        >确认</el-button
      >
    </template>
  </el-dialog>
</template>

<script>
import axios from "axios";
import { ElMessage } from "element-plus";
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
      // 多选选中的id
      selectId: [],
      //表单加载
      tableLoading: false,
      //关键词
      keyword: "",
      //商品详细信息窗口
      goodDetailDialog: false,
      //显示的商品信息
      good: {
        goodName: "",
        price: "",
        categoryName: "",
        description: "",
        payType: "",
        userUsername: "",
        goodImg: "",
        createTime: "",
      },
      userDetailDialog: false,
      //卖家详细信息
      saleUser: {},
    };
  },
  methods: {
    //获取所有订单的数据
    getAllOrder() {
      let that = this;
      axios({
        url:
          this.$store.state.localhost +
          "/order/all?num=" +
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
    //批量删除
    deleteOrdersByBatchIds() {
      let that = this;
      //拼接字符串，用于请求的参数
      this.$confirm("此操作将永久删除这些订单, 是否继续?", "提示", {
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
            url: this.$store.state.localhost + "/order/delete/ids?ids=" + ids,
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
    //多选栏，选中触发
    handleSelectionChange(val) {
      let temp = [];
      for (let i = 0; i < val.length; i++) {
        temp[i] = val[i].orderId;
      }
      this.selectId = temp;
    },
    //删除指定订单
    deleteOrder(id) {
      let that = this;
      this.$confirm("此操作将永久删除该订单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          axios({
            url: this.$store.state.localhost + "/order/delete?orderId=" + id,
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
          this.$store.state.localhost + "/order/total?keyword=" + this.keyword,
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          that.total = res.data.data;
        }
      });
    },
    //显示商品的详细信息
    showGoodDetailInfo(goodId) {
      this.goodDetailDialog = true;
      let that = this;
      //获取商品的详细信息请求
      axios({
        url: this.$store.state.localhost + "/good/detail?goodId=" + goodId,
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          that.good = res.data.data;
        }
      });
    },
    //显示卖家的详细信息
    showSaleDetaileInfo(userId) {
      this.userDetailDialog = true;
      let that = this;
      //获取商品的详细信息请求
      axios({
        url: this.$store.state.localhost + "/user/info?userId=" + userId,
        method: "get",
        withCredentials: true,
      }).then(function (res) {
        if (res.data.status) {
          that.saleUser = res.data.data;
        }
      });
    },
  },
  mounted() {
    this.reloadOrder();
  },
};
</script>

<style>
.goodName:hover {
  cursor: pointer;
  color: #44a7ff;
}
.good-detail-content {
  margin-left: 10%;
}
.good-detail-content > div {
  margin: 15px 10px;
}
/* 商品详细信息 */
.good-detail-content > div > .good-img-title {
  position: relative !important;
  bottom: 65px !important;
}
.good-detail-content > div > .good-img {
  position: relative !important;
  left: 10px !important;
}
.saleUsername:hover {
  cursor: pointer;
  color: #44a7ff;
}
</style>
