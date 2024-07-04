<template>
  <div class="detection-page">
    <h1 class="title">
      <i class="fas fa-table"></i> 检测页面
    </h1>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="name" label="文件名称" width="100"></el-table-column>
      <el-table-column prop="style" label="文件类型" width="100"></el-table-column>
      <el-table-column prop="path" label="下载地址"></el-table-column>
      <el-table-column prop="createtime" label="检测时间" width="180"></el-table-column>
      <el-table-column prop="result" label="检测结果(真人概率)" min-width="16%">
        <template slot-scope="scope">
          <div :class="{'result-high': scope.row.result > 50, 'result-low': scope.row.result <= 50}">
            {{ scope.row.result }}%
          </div>
        </template>
      </el-table-column>    </el-table>
    <el-pagination
      v-if="total > params.pageSize"
      layout="total, prev, pager, next, jumper"
      :total="total"
      :current-page="params.pageNum"
      :page-size="params.pageSize"
      @current-change="handlePageChange"
      class="pagination"
    />
  </div>
</template>

<script>
import Cookies from "js-cookie";
import request from "../../utils/request";

export default {
  data() {
    return {
      user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},

      tableData: [],

      total:0,

      params:{
        pageNum:1,
        pageSize:4,
        style:'',
        status:'',
        pid:'',
      },
    };
  },
  created() {
    this.load()
  },

  computed: {},
  methods: {

    load(){
      this.params.pid = this.user.id;
      request.get('/detection/page',{params : this.params}).then(res => {
        if(res.code === '200'){
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    handlePageChange(page) {
      this.params.pageNum = page;
      this.load();
    }
  }
};
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css');

.detection-page {
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 3px 8px rgba(0, 0, 0, 0.15);
  background-color: rgba(255, 255, 255, 0.9);
  text-align: center;
  animation: fadeIn 1s ease-in-out;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

.title {
  font-size: 28px;
  margin-bottom: 20px;
  color: #333;
}

.el-table {
  margin-top: 20px;
}

.el-pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

.result-high {
  color: #3498db;
  font-weight: bold;
}

.result-low {
  color: #e74c3c;
  font-weight: bold;
}

.el-table th,
.el-table td {
  text-align: center;
}

.el-table-column {
  text-align: center;
}
</style>
