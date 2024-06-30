<template>
  <div class="detection-page">
    <h1>统一检测界面</h1>
    <el-table :data="pagedDetectionData" style="width: 100%">
      <el-table-column prop="id" label="id" min-width="10%"></el-table-column>
      <el-table-column prop="name" label="文件名称" min-width="15%"></el-table-column>
      <el-table-column prop="style" label="检测类型" min-width="15%"></el-table-column>
      <el-table-column prop="status" label="检测状态" min-width="10%"></el-table-column>
      <el-table-column prop="createtime" label="检测时间" min-width="25%"></el-table-column>
      <el-table-column prop="result" label="检测结果" min-width="30%">
        <template slot-scope="scope">
          <div :class="{'result-high': scope.row.result > 50, 'result-low': scope.row.result <= 50}">
            {{ scope.row.result }}%
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-if="detectionData.length > params.pageSize"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="params.pageNum"
      :page-size="params.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="detectionData.length"
      class="pagination">
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      detectionData: [
        {id:1, name: 'image1.jpg', style: '图像检测', status: '正在检测', createtime: '2023-06-01 10:00:00', result: 0 },
        {id:2, name: 'video1.mp4', style: '视频检测', status: '已检测完毕', createtime: '2023-06-01 10:30:00', result: 80 },
        {id:3, name: 'audio1.mp3', style: '音频检测', status: '已检测完毕', createtime: '2023-06-01 11:00:00', result: 30 },
        {id:4, name: 'image2.jpg', style: '图像检测', status: '已检测完毕', createtime: '2023-06-01 12:00:00', result: 45 },
        {id:5, name: 'video2.mp4', style: '视频检测', status: '正在检测', createtime: '2023-06-01 13:00:00', result: 0 },
        {id:6, name: 'audio2.mp3', style: '音频检测', status: '已检测完毕', createtime: '2023-06-01 14:00:00', result: 60 }
      ],
      params:{
        pageNum:1,
        pageSize:5,
      },

    };
  },
  computed: {
    pagedDetectionData() {
      const start = (this.params.pageNum - 1) * this.params.pageSize;
      const end = start + this.params.pageSize;
      return this.detectionData.slice(start, end);
    }
  },
  methods: {
    handleSizeChange(size) {
      this.params.pageSize = size;
    },
    handleCurrentChange(page) {
      this.params.pageNum = page;
    }
  }
};
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css');

.detection-page {
  width: 100%;
  height: 100vh;
  margin: 0;
  padding: 20px;
  background-image: url('https://img0.baidu.com/it/u=3884164376,1033547386&fm=253&fmt=auto&app=138&f=JPEG?w=794&h=350');
  background-size: cover;
  background-position: center;
  border-radius: 10px;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.15);
  text-align: center;
  color: #fff;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

h1 {
  color: #fff;
  font-size: 32px;
  margin-bottom: 30px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.el-table {
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 10px;
  overflow: hidden;
  width: 100%;
  table-layout: fixed;
}

.el-table-column {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.result-high {
  color: #e74c3c;
  font-weight: bold;
}

.result-low {
  color: #3498db;
  font-weight: bold;
}

.pagination {
  margin-top: 20px;
  background-color: rgba(255, 255, 255, 0.9);
  padding: 10px;
  border-radius: 10px;
}

.el-pagination .el-pager li:not(.active) {
  background-color: rgba(255, 255, 255, 0.9);
  color: #333;
}

.el-pagination .el-pager li.active {
  background-color: #3498db;
  color: #fff;
}

.el-pagination .el-pager li:hover {
  background-color: #ddd;
}

@media (max-width: 768px) {
  .detection-page {
    padding: 10px;
  }

  h1 {
    font-size: 24px;
  }

  .el-table {
    margin-top: 10px;
  }

  .pagination {
    padding: 5px;
  }
}
</style>
