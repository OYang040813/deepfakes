<template>
  <div class="message-page">
    <header class="header">
      <h1>个人消息</h1>
      <button @click="markAllAsRead" class="btn-mark-all">全部标为已读</button>
    </header>

<!--    <nav class="message-tabs">-->
<!--      <button-->
<!--        v-for="tab in tabs"-->
<!--        :key="tab.id"-->
<!--        :class="['tab-btn', { active: currentTab === tab.id }]"-->
<!--        @click="currentTab = tab.id"-->
<!--      >-->
<!--        {{ tab.name }}-->
<!--        <span v-if="tab.unread" class="unread-badge">{{ tab.unread }}</span>-->
<!--      </button>-->
<!--    </nav>-->

<!--    <div class="message-list" v-if="filteredMessages.length">-->
<!--      <div-->
<!--        v-for="message in filteredMessages"-->
<!--        :key="message.id"-->
<!--        :class="['message-item', { unread: !message.read }]"-->
<!--        @click="readMessage(message)"-->
<!--      >-->
<!--        <div class="message-icon" :class="message.type">-->
<!--          <i :class="getIconClass(message.type)"></i>-->
<!--        </div>-->
<!--        <div class="message-content">-->
<!--          <h3 class="message-title">{{ message.title }}</h3>-->
<!--          <p class="message-preview">{{ message.content }}</p>-->
<!--          <span class="message-time">{{ formatDate(message.time) }}</span>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div v-else class="no-messages">-->
<!--      当前没有消息-->
<!--    </div>-->

<!--    <div class="pagination" v-if="totalPages > 1">-->
<!--      <button-->
<!--        v-for="page in totalPages"-->
<!--        :key="page"-->
<!--        :class="['page-btn', { active: currentPage === page }]"-->
<!--        @click="currentPage = page"-->
<!--      >-->
<!--        {{ page }}-->
<!--      </button>-->
<!--    </div>-->
  </div>
</template>

<!--<script setup>-->
<!--import { ref, computed } from 'vue';-->
<!--import { formatDistanceToNow } from 'date-fns';-->
<!--import { zhCN } from 'date-fns/locale';-->

<!--const tabs = [-->
<!--  { id: 'all', name: '全部', unread: 5 },-->
<!--  { id: 'system', name: '系统', unread: 2 },-->
<!--  { id: 'notification', name: '通知', unread: 3 },-->
<!--];-->

<!--const messages = ref([-->
<!--  { id: 1, type: 'system', title: '系统升级通知', content: '系统将于今晚23:00进行升级维护...', time: new Date('2023-05-20T10:00:00'), read: false },-->
<!--  { id: 2, type: 'notification', title: '新评论提醒', content: '您的文章收到了新的评论...', time: new Date('2023-05-19T14:30:00'), read: true },-->
<!--  // ... 更多消息-->
<!--]);-->

<!--const currentTab = ref('all');-->
<!--const currentPage = ref(1);-->
<!--const itemsPerPage = 10;-->

<!--const filteredMessages = computed(() => {-->
<!--  let filtered = messages.value;-->
<!--  if (currentTab.value !== 'all') {-->
<!--    filtered = filtered.filter(m => m.type === currentTab.value);-->
<!--  }-->
<!--  const start = (currentPage.value - 1) * itemsPerPage;-->
<!--  return filtered.slice(start, start + itemsPerPage);-->
<!--});-->
<!--const totalPages = computed(() => Math.ceil(messages.value.length / itemsPerPage));-->

<!--const getIconClass = (type) => {-->
<!--  switch (type) {-->
<!--    case 'system': return 'fas fa-cog';-->
<!--    case 'notification': return 'fas fa-bell';-->
<!--    default: return 'fas fa-envelope';-->
<!--  }-->
<!--};-->

<!--const formatDate = (date) => {-->
<!--  return formatDistanceToNow(date, { addSuffix: true, locale: zhCN });-->
<!--};-->

<!--const readMessage = (message) => {-->
<!--  message.read = true;-->
<!--  // 这里可以添加向后端发送已读状态的逻辑-->
<!--};-->

<!--const markAllAsRead = () => {-->
<!--  messages.value.forEach(message => message.read = true);-->
<!--  // 这里可以添加向后端发送全部已读的逻辑-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.message-page {-->
<!--  max-width: 800px;-->
<!--  margin: 0 auto;-->
<!--  padding: 20px;-->
<!--  font-family: Arial, sans-serif;-->
<!--}-->

<!--.header {-->
<!--  display: flex;-->
<!--  justify-content: space-between;-->
<!--  align-items: center;-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--h1 {-->
<!--  font-size: 24px;-->
<!--  color: #333;-->
<!--}-->

<!--.btn-mark-all {-->
<!--  background-color: #f0f0f0;-->
<!--  border: none;-->
<!--  padding: 8px 16px;-->
<!--  border-radius: 4px;-->
<!--  cursor: pointer;-->
<!--  transition: background-color 0.3s;-->
<!--}-->

<!--.btn-mark-all:hover {-->
<!--  background-color: #e0e0e0;-->
<!--}-->

<!--.message-tabs {-->
<!--  display: flex;-->
<!--  margin-bottom: 20px;-->
<!--  border-bottom: 1px solid #e0e0e0;-->
<!--}-->

<!--.tab-btn {-->
<!--  padding: 10px 20px;-->
<!--  background: none;-->
<!--  border: none;-->
<!--  cursor: pointer;-->
<!--  font-size: 16px;-->
<!--  color: #666;-->
<!--  position: relative;-->
<!--}-->

<!--.tab-btn.active {-->
<!--  color: #1a73e8;-->
<!--  font-weight: bold;-->
<!--}-->

<!--.tab-btn.active::after {-->
<!--  content: '';-->
<!--  position: absolute;-->
<!--  bottom: -1px;-->
<!--  left: 0;-->
<!--  right: 0;-->
<!--  height: 3px;-->
<!--  background-color: #1a73e8;-->
<!--}-->

<!--.unread-badge {-->
<!--  background-color: #f44336;-->
<!--  color: white;-->
<!--  border-radius: 50%;-->
<!--  padding: 2px 6px;-->
<!--  font-size: 12px;-->
<!--  margin-left: 5px;-->
<!--}-->

<!--.message-list {-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  gap: 10px;-->
<!--}-->

<!--.message-item {-->
<!--  display: flex;-->
<!--  align-items: flex-start;-->
<!--  padding: 15px;-->
<!--  background-color: #fff;-->
<!--  border: 1px solid #e0e0e0;-->
<!--  border-radius: 8px;-->
<!--  cursor: pointer;-->
<!--  transition: box-shadow 0.3s;-->
<!--}-->

<!--.message-item:hover {-->
<!--  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);-->
<!--}-->

<!--.message-item.unread {-->
<!--  background-color: #f0f8ff;-->
<!--}-->

<!--.message-icon {-->
<!--  width: 40px;-->
<!--  height: 40px;-->
<!--  border-radius: 50%;-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  justify-content: center;-->
<!--  margin-right: 15px;-->
<!--  font-size: 20px;-->
<!--}-->
<!--.message-icon.system {-->
<!--  background-color: #e1f5fe;-->
<!--  color: #0288d1;-->
<!--}-->

<!--.message-icon.notification {-->
<!--  background-color: #fff3e0;-->
<!--  color: #ff9800;-->
<!--}-->

<!--.message-content {-->
<!--  flex: 1;-->
<!--}-->

<!--.message-title {-->
<!--  font-size: 16px;-->
<!--  font-weight: bold;-->
<!--  margin: 0 0 5px;-->
<!--  color: #333;-->
<!--}-->

<!--.message-preview {-->
<!--  font-size: 14px;-->
<!--  color: #666;-->
<!--  margin: 0 0 5px;-->
<!--  white-space: nowrap;-->
<!--  overflow: hidden;-->
<!--  text-overflow: ellipsis;-->
<!--}-->

<!--.message-time {-->
<!--  font-size: 12px;-->
<!--  color: #999;-->
<!--}-->

<!--.no-messages {-->
<!--  text-align: center;-->
<!--  padding: 40px;-->
<!--  color: #666;-->
<!--  font-style: italic;-->
<!--}-->

<!--.pagination {-->
<!--  display: flex;-->
<!--  justify-content: center;-->
<!--  margin-top: 20px;-->
<!--}-->

<!--.page-btn {-->
<!--  padding: 5px 10px;-->
<!--  margin: 0 5px;-->
<!--  background-color: #f0f0f0;-->
<!--  border: none;-->
<!--  border-radius: 4px;-->
<!--  cursor: pointer;-->
<!--  transition: background-color 0.3s;-->
<!--}-->

<!--.page-btn:hover {-->
<!--  background-color: #e0e0e0;-->
<!--}-->

<!--.page-btn.active {-->
<!--  background-color: #1a73e8;-->
<!--  color: white;-->
<!--}-->
<!--</style>-->
