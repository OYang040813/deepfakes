<template>
  <div class="admin-page">
    <el-row>
      <el-col :span="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix header">
            <span>管理员功能</span>
          </div>
          <el-tabs type="card">
            <el-tab-pane label="发布系统消息">
              <div class="tab-content post-message">
                <el-form ref="form" :model="form" label-width="120px">
                  <el-form-item label="消息标题">
                    <el-input v-model="form.title"></el-input>
                  </el-form-item>
                  <el-form-item label="消息内容">
                    <el-input type="textarea" v-model="form.content"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="handlePostMessage">发布</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-tab-pane>

            <el-tab-pane label="用户管理">
              <div class="tab-content manage-users">
                <el-button type="primary" @click="handleAddUser">添加用户</el-button>
                <el-table :data="users" style="width: 100%">
                  <el-table-column prop="name" label="用户名" width="150"></el-table-column>
                  <el-table-column prop="role" label="角色" width="100"></el-table-column>
                  <el-table-column prop="email" label="邮箱" width="200"></el-table-column>
                  <el-table-column label="操作" width="180">
                    <template slot-scope="scope">
                      <el-button size="mini" @click="handleEditUser(scope.row)">编辑</el-button>
                      <el-button size="mini" type="danger" @click="handleDeleteUser(scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-tab-pane>

            <el-tab-pane label="系统日志">
              <div class="tab-content system-logs">
                <el-table :data="logs" style="width: 100%">
                  <el-table-column prop="timestamp" label="时间" width="180"></el-table-column>
                  <el-table-column prop="level" label="级别" width="100"></el-table-column>
                  <el-table-column prop="message" label="信息"></el-table-column>
                </el-table>
              </div>
            </el-tab-pane>

            <el-tab-pane label="系统设置">
              <div class="tab-content system-settings">
                <el-form ref="settingsForm" :model="settingsForm" label-width="120px">
                  <el-form-item label="参数1">
                    <el-input v-model="settingsForm.param1"></el-input>
                  </el-form-item>
                  <el-form-item label="参数2">
                    <el-input v-model="settingsForm.param2"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="handleSaveSettings">保存设置</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-tab-pane>

            <el-tab-pane label="检测记录">
              <div class="tab-content detection-records">
                <el-table :data="detectionRecords" style="width: 100%">
                  <el-table-column prop="filename" label="文件名" width="180"></el-table-column>
                  <el-table-column prop="detectionTime" label="检测时间" width="180"></el-table-column>
                  <el-table-column prop="result" label="结果"></el-table-column>
                </el-table>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        title: '',
        content: ''
      },
      users: [
        { name: 'user1', role: 'admin', email: 'user1@example.com' },
        { name: 'user2', role: 'user', email: 'user2@example.com' }
      ],
      logs: [
        { timestamp: '2024-06-24 10:00:00', level: 'info', message: '系统启动' },
        { timestamp: '2024-06-24 10:05:00', level: 'error', message: '检测失败' }
      ],
      settingsForm: {
        param1: 'value1',
        param2: 'value2'
      },
      detectionRecords: [
        { filename: 'file1.mp4', detectionTime: '2024-06-24 10:00:00', result: '通过' },
        { filename: 'file2.jpg', detectionTime: '2024-06-24 09:45:00', result: '未通过' }
      ]
    };
  },
  methods: {
    handlePostMessage() {
      // 发布系统消息的逻辑
      console.log('发布系统消息:', this.form);
    },
    handleAddUser() {
      // 添加用户的逻辑
      console.log('添加用户');
    },
    handleEditUser(user) {
      // 编辑用户的逻辑
      console.log('编辑用户:', user);
    },
    handleDeleteUser(user) {
      // 删除用户的逻辑
      console.log('删除用户:', user);
    },
    handleSaveSettings() {
      // 保存系统设置的逻辑
      console.log('保存系统设置:', this.settingsForm);
    }
  }
};
</script>

<style scoped>
.admin-page {
  padding: 20px;
  background-color: #f5f5f5;
}

.header {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  color: #409EFF;
}

.tab-content {
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.post-message .el-form-item__label {
  color: #409EFF;
}

.manage-users .el-button--primary {
  background-color: #67C23A;
  border-color: #67C23A;
}

.system-logs .el-table__header-wrapper {
  background-color: #E6A23C;
}

.system-settings .el-form-item__label {
  color: #F56C6C;
}

.detection-records .el-table__header-wrapper {
  background-color: #909399;
}

.el-tabs__item.is-active {
  color: #409EFF !important;
  font-weight: bold;
}
</style>
