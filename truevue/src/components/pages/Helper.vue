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
          <li>功能1：描述功能1的用途和使用方法。</li>
          <li>功能2：描述功能2的用途和使用方法。</li>
          <li>功能3：描述功能3的用途和使用方法。</li>
        </ul>
      </section>
      <section v-if="currentPage === 3">
        <h2>常见问题</h2>
        <ul>
          <li>
            <strong>问题1：</strong>如何解决问题1？
            <p>解答：步骤1，步骤2，步骤3。</p>
          </li>
          <li>
            <strong>问题2：</strong>如何解决问题2？
            <p>解答：步骤1，步骤2，步骤3。</p>
          </li>
        </ul>
      </section>
      <section v-if="currentPage === 4">
        <h2>联系方式</h2>
        <p>如果您有任何疑问，请通过以下方式联系我们：</p>
        <ul>
          <li>电话：</li>
          <li>邮箱：</li>
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
      totalPages: 4 // Total number of sections
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
