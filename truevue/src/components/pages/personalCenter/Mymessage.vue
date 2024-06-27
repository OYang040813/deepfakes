<template>
  <div class="message-management">
    <h1>个人消息管理</h1>
    <div class="message-list">
      <div v-for="message in pagedMessages" :key="message.id" class="message-item">
        <div class="message-header">
          <span class="message-title">{{ message.title }}</span>
          <span class="message-date">{{ message.date }}</span>
        </div>
        <p class="message-content">{{ message.content }}</p>
        <div class="message-actions">
          <button @click="markAsRead(message.id)">标记为已读</button>
          <button @click="deleteMessage(message.id)">删除</button>
        </div>
      </div>
    </div>
    <div v-if="messages.length === 0" class="no-messages">
      暂无消息
    </div>
    <el-pagination
      v-if="messages.length > pageSize"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="messages.length">
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      messages: [
        { id: 1, title: "系统通知", content: "您的账户已成功激活。", date: "2023-05-10", read: false },
        { id: 2, title: "新消息", content: "您有一条新的好友请求。", date: "2023-05-11", read: false },
        { id: 3, title: "安全提醒", content: "请定期更改您的密码以确保账户安全。", date: "2023-05-12", read: false },
        { id: 4, title: "系统通知", content: "您的账户已成功激活。", date: "2023-05-10", read: false },
        { id: 5, title: "新消息", content: "您有一条新的好友请求。", date: "2023-05-11", read: false },
        { id: 6, title: "安全提醒", content: "请定期更改您的密码以确保账户安全。", date: "2023-05-12", read: false },
        { id: 7, title: "系统通知", content: "您的账户已成功激活。", date: "2023-05-10", read: false },
        { id: 8, title: "新消息", content: "您有一条新的好友请求。", date: "2023-05-11", read: false },
        { id: 9, title: "安全提醒", content: "请定期更改您的密码以确保账户安全。", date: "2023-05-12", read: false },
        { id: 10, title: "系统通知", content: "您的账户已成功激活。", date: "2023-05-10", read: false },
        { id: 11, title: "新消息", content: "您有一条新的好友请求。", date: "2023-05-11", read: false },
        { id: 12, title: "安全提醒", content: "请定期更改您的密码以确保账户安全。", date: "2023-05-12", read: false },
      ],
      currentPage: 1,
      pageSize: 5
    };
  },
  computed: {
    pagedMessages() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.messages.slice(start, end);
    }
  },
  methods: {
    markAsRead(id) {
      const message = this.messages.find(m => m.id === id);
      if (message) {
        message.read = true;
      }
    },
    deleteMessage(id) {
      this.messages = this.messages.filter(m => m.id !== id);
    },
    handleSizeChange(size) {
      this.pageSize = size;
    },
    handleCurrentChange(page) {
      this.currentPage = page;
    }
  }
}
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css');

.message-management {
  font-family: Arial, sans-serif;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f7f9fc;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  animation: fadeIn 1s ease-in-out;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

h1 {
  color: #333;
  text-align: center;
  font-size: 28px;
  margin-bottom: 30px;
}

.message-list {
  margin-top: 20px;
}

.message-item {
  background-color: #ffffff;
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 15px;
  margin-bottom: 10px;
  transition: transform 0.3s, box-shadow 0.3s;
}

.message-item:hover {
  transform: scale(1.02);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.message-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.message-title {
  font-weight: bold;
  color: #444;
}

.message-date {
  color: #888;
  font-size: 0.9em;
}

.message-content {
  color: #555;
  margin-bottom: 10px;
  line-height: 1.6;
}

.message-actions {
  display: flex;
  justify-content: flex-end;
}

button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 8px 12px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  margin-left: 10px;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s, transform 0.3s;
}

button:hover {
  background-color: #45a049;
  transform: scale(1.05);
}

.no-messages {
  text-align: center;
  color: #888;
  margin-top: 20px;
  padding: 20px;
  background-color: #f0f0f0;
  border-radius: 10px;
}

.message-actions button:first-child {
  background-color: #3498db;
}

.message-actions button:first-child:hover {
  background-color: #2980b9;
}

.message-actions button:last-child {
  background-color: #e74c3c;
}

.message-actions button:last-child:hover {
  background-color: #c0392b;
}

.el-pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

@media (max-width: 600px) {
  .message-management {
    padding: 10px;
  }

  .message-item {
    padding: 10px;
  }

  .message-header {
    flex-direction: column;
  }

  .message-date {
    margin-top: 5px;
  }

  .message-actions {
    flex-direction: column;
    align-items: stretch;
  }

  .message-actions button {
    margin: 5px 0;
  }
}
</style>
