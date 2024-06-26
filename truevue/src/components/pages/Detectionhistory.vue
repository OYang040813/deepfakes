<template>
  <div class="detection-page">
    <h1 class="title">
      <i class="fas fa-table"></i> 检测页面
    </h1>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="name" label="文件名称" width="150"></el-table-column>
      <el-table-column prop="style" label="文件类型" width="100"></el-table-column>
      <el-table-column prop="path" label="路径" width="250"></el-table-column>
      <el-table-column prop="createtime" label="检测时间" width="180"></el-table-column>
      <el-table-column prop="result" label="检测结果" width="100"></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        { name: 'file1.mp4', style: '视频', path: '/videos/file1.mp4', createtime: '2023-06-01 10:00:00', result: 85 },
        { name: 'file2.jpg', style: '图片', path: '/images/file2.jpg', createtime: '2023-06-02 11:30:00', result: 70 },
        { name: 'file3.mp3', style: '音频', path: '/audio/file3.mp3', createtime: '2023-06-03 14:20:00', result: 90 },
        { name: 'file4.mp4', style: '视频', path: '/videos/file4.mp4', createtime: '2023-06-04 15:45:00', result: 60 }
      ]
    };
  }
};
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css');

.detection-page {
  max-width: 1200px;
  margin: 20px auto;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 3px 8px rgba(0, 0, 0, 0.15);
  background-color: rgba(255, 255, 255, 0.9);
  text-align: center;
}

.title {
  font-size: 28px;
  margin-bottom: 20px;
  color: #333;
}

.el-table {
  margin-top: 10px;
}
</style>
