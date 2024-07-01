<template>
  <div class="detection-page">
    <h1 class="title">
      <i class="fas fa-table"></i> 检测页面
    </h1>
    <el-table :data="paginatedData" style="width: 100%">
      <el-table-column prop="name" label="文件名称" width="150"></el-table-column>
      <el-table-column prop="style" label="文件类型" width="100"></el-table-column>
      <el-table-column prop="path" label="下载地址"></el-table-column>
      <el-table-column prop="createtime" label="检测时间" width="100"></el-table-column>
      <el-table-column prop="result" label="检测结果" width="100"></el-table-column>
    </el-table>
    <el-pagination
      v-if="tableData.length > params.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tableData.length"
      :current-page="params.pageNum"
      :page-size="params.pageSize"
      @size-change="handleSizeChange"
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

      tableData: [
        { name: 'file1.mp4', style: '视频', path: '/videos/file1.mp4', createtime: '2023-06-01 10:00:00', result: 85 },
        { name: 'file2.jpg', style: '图片', path: '/images/file2.jpg', createtime: '2023-06-02 11:30:00', result: 70 },
        { name: 'file3.mp3', style: '音频', path: '/audio/file3.mp3', createtime: '2023-06-03 14:20:00', result: 90 },
        { name: 'file4.mp4', style: '视频', path: '/videos/file4.mp4', createtime: '2023-06-04 15:45:00', result: 60 },
        // Add more data as needed
      ],

      params:{
        pageNum:1,
        pageSize:5,
        // style:'',
        // status:'',
        pid:'',
      },
    };
  },
  created() {
    this.load()
  },
  computed: {
    paginatedData() {
      const start = (this.params.pageNum - 1) * this.params.pageSize;
      const end = start + this.params.pageSize;
      return this.tableData.slice(start, end);
    }
  },
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

    handleSizeChange(size) {
      this.params.pageSize = size;
    },
    handlePageChange(page) {
      this.params.pageNum = page;
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

.el-table th,
.el-table td {
  text-align: center;
}

.el-table-column {
  text-align: center;
}
</style>
