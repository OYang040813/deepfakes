<template>
  <div id="user-guide">
    <header>
      <h1>用户使用说明</h1>
    </header>
    <main>
      <section v-if="currentPage === 1">
        <h2>简介</h2>
        <p>本说明书将指导您如何使用我们的系统。</p>
      </section>
      <section v-if="currentPage === 2">
        <h2>功能介绍</h2>
        <ul>
          <li>上传照片：上传可疑的图片，系统将返回臆造的概率。</li>
          <li>上传视频：上传可疑的视频，系统将截取其中的关键帧，圈出关键的头像。</li>
          <li>上传音频：上传一段音频，系统将判断是否为真人发出的声音。</li>
        </ul>
      </section>
      <section v-if="currentPage === 3">
        <h2>常见问题</h2>
        <ul>
          <li>
            <strong>问题1：</strong>内容违规(视频，图片不过审核)
            <p>视频和图片上传之后系统会有短时间的审核机制，请耐心等待。如果发现有违规的内容出现，系统便会提示内容违规并返回该文件。</p>
          </li>
          <li>
            <strong>问题2：</strong>文件上传失败
            <p>系统对于图片、视频及音频的上传有对应的格式要求，请仔细检查您需上传的文件，如不符合要求可能上传失败</p>
          </li>
        </ul>
      </section>
    </main>
    <footer>
      <el-pagination
        background
        layout="prev, pager, next"
        :total="totalPages"
        :page-size="1"
        @current-change="handlePageChange"
      />
      <p>&copy; 2024 </p>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'UserGuide',
  data() {
    return {
      currentPage: 1,
      totalPages: 3 // Total number of sections
    };
  },
  methods: {
    handlePageChange(page) {
      this.currentPage = page;
    }
  }
};
</script>

<style scoped>
#user-guide {
  font-family: Arial, sans-serif;
  line-height: 1.6;
  padding: 20px;
  background-color: #f0f2f5;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  animation: fadeIn 1s ease-in-out;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

h1 {
  color: #f6f6f6;
  text-align: center;
  font-size: 28px;
  margin-bottom: 30px;
}

header {
  background-color: #325f8f;
  color: white;
  padding: 10px 0;
  text-align: center;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

main {
  margin-top: 20px;
}

h2 {
  color: #419c9f;
}

ul {
  list-style-type: none;
  padding: 0;
}

ul li {
  margin-bottom: 10px;
}

footer {
  margin-top: 20px;
  text-align: center;
  color: #777;
}

.el-pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>
