<template>
  <div class="message-management">
    <h1>个人消息管理</h1>
    <div class="message-list">
      <div v-for="message in pagedMessages" :key="message.id" :class="['message-item', { 'message-read': message.isread == 1 }]">
        <div class="message-header">
          <span class="message-style">{{ message.style }}</span>
          <span class="message-createtime">{{ message.createtime }}</span>
        </div>
        <p class="message-mes">{{ message.mes }}</p>
        <div class="message-actions">
          <button v-if="message.isread == 0" @click="markAsRead(message.id)">标记为已读</button>
          <button @click="deleteMessage(message.id)">删除</button>
        </div>
      </div>
    </div>
    <div v-if="messages.length == 0" class="no-messages">
      暂无消息
    </div>
    <el-pagination
      v-if="messages.length > params.pageSize"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="params.currentPage"
      :page-size="params.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
import request from "../../../utils/request";
import Cookies from "js-cookie";

export default {
  data() {
    return {

      user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},

      messages: [],
      params: {
        currentPage: 1,
        pageSize: 5,
        style: '',
        createtime: '',
        isread: '',
        pid:'',
      },
      total: 0,
    };
  },
  created() {
    this.load();
  },
  computed: {
    pagedMessages() {
      const start = (this.params.currentPage - 1) * this.params.pageSize;
      const end = start + this.params.pageSize;
      return this.messages.slice(start, end);
    }
  },
  methods: {
    load() {
      this.params.pid = this.user.id;
      request.get('/message/page', { params: this.params }).then(res => {
        if (res.code === '200') {
          this.messages = res.data.list;
          this.total = res.data.total;
        }
      });
    },
    markAsRead(id) {
      const message = this.messages.find(m => m.id === id);
      if (message) {
        message.isread = 1;
        request.put("/message/mark/" + id).then(res => {
          if (res.code === '200') {
            this.$notify.success("标记成功");
            this.load();
          } else {
            this.$notify.error(res.msg);
          }
        });
      }
    },
    deleteMessage(id) {
      this.messages = this.messages.filter(m => m.id !== id);
      request.delete("/message/delete/" + id).then(res => {
        if (res.code === '200') {
          this.$notify.success("删除成功");
          this.load(); // 调用刷新函数
        } else {
          this.$notify.error(res.msg);
        }
      });
    },
    handleSizeChange(size) {
      this.params.pageSize = size;
      this.load();
    },
    handleCurrentChange(page) {
      this.params.currentPage = page;
      this.load();
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

.message-item.message-read {
  background-color: #e0ffe0;
}

.message-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.message-style {
  font-weight: bold;
  color: #444;
}

.message-createtime {
  color: #888;
  font-size: 0.9em;
}

.message-mes {
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

  .message-createtime {
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
