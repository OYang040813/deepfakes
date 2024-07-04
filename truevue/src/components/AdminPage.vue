<template>
  <div class="admin-page">
    <el-row>
      <el-col :span="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix header">
            <span>管理员功能</span>
          </div>
          <el-tabs type="card">
            <el-tab-pane label="发布系统公告">
              <div class="tab-content post-message">
                <el-form ref="form" :model="form" label-width="120px">
                  <el-form-item label="消息标题">
                    <el-input v-model="form.title"></el-input>
                  </el-form-item>
                  <el-form-item label="消息内容">
                    <el-input type="textarea" v-model="form.mes"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="confirmPostMessage">发布</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-tab-pane>

            <el-tab-pane label="用户管理">
              <div class="tab-content manage-users">
                <el-button type="primary" @click="showAddUserDialog">添加用户</el-button>
                <el-table :data="users" style="width: 100%">
                  <el-table-column prop="name" label="用户名" width="150"></el-table-column>
<!--                  <el-table-column prop="IsAuth" label="角色" width="100"></el-table-column>-->
                  <el-table-column prop="isAuth" label="角色" width="100">
                    <template slot-scope="scope">
                      {{ scope.row.isAuth === 1 ? 'Admin' : (scope.row.isAuth === 0 ? 'User' : 'Unknown') }}
                    </template>
                  </el-table-column>
                  <el-table-column prop="email" label="邮箱" width="200"></el-table-column>
                  <el-table-column label="操作" width="180">
                    <template slot-scope="scope">
                      <el-button size="mini" @click="handleEditUser(scope.row)">编辑</el-button>

                      <el-popconfirm
                        style="margin-left: 7px"
                        confirm-button-text='确认'
                        cancel-button-text='取消'
                        cancel-button-type="Primary"
                        icon="el-icon-delete-solid"
                        title="您确定删除该用户吗？"
                        @confirm="handleDeleteUser(scope.row)"
                      >
                        <el-button size="mini" type="danger" slot="reference">删除</el-button>
                      </el-popconfirm>

                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-tab-pane>

            <el-tab-pane label="系统公告">
              <div class="tab-content system-logs">
                <el-table :data="announcement" style="width: 100%">
                  <el-table-column prop="title" label="标题" width="400"></el-table-column>
                  <el-table-column prop="mes" label="信息"></el-table-column>
                  <el-table-column prop="createtime" label="时间" width="160"></el-table-column>
                </el-table>
              </div>
            </el-tab-pane>

<!--            <el-tab-pane label="系统设置">-->
<!--              <div class="tab-content system-settings">-->
<!--                <el-form ref="settingsForm" :model="settingsForm" label-width="120px">-->
<!--                  <el-form-item label="参数1">-->
<!--                    <el-input v-model="settingsForm.param1"></el-input>-->
<!--                  </el-form-item>-->
<!--                  <el-form-item label="参数2">-->
<!--                    <el-input v-model="settingsForm.param2"></el-input>-->
<!--                  </el-form-item>-->
<!--                  <el-form-item>-->
<!--                    <el-button type="primary" @click="handleSaveSettings">保存设置</el-button>-->
<!--                  </el-form-item>-->
<!--                </el-form>-->
<!--              </div>-->
<!--            </el-tab-pane>-->

            <el-tab-pane label="检测记录">
              <div class="tab-content detection-records">
                <el-table :data="detectionRecords" style="width: 100%">
                  <el-table-column prop="cardnum" label="检测号" width="250"></el-table-column>
                  <el-table-column prop="name" label="文件名" width="380"></el-table-column>
                  <el-table-column prop="createtime" label="检测时间" width="250"></el-table-column>
                  <el-table-column prop="result" label="结果">
                    <template slot-scope="scope">
                      {{ scope.row.result === null ? "待检测" : scope.row.result }}
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>

    <!-- 添加用户弹出框 -->
    <el-dialog title="添加用户" :visible.sync="addUserDialogVisible">
      <el-form ref="addUserForm" :model="addUserForm" label-width="120px">
        <el-form-item label="用户名">
          <el-input v-model="addUserForm.name"></el-input>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="addUserForm.isAuth" placeholder="请选择角色">
            <el-option label="Admin" value="1"></el-option>
            <el-option label="User" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="addUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="addUserForm.keynum" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAddUserConfirm">确认</el-button>
          <el-button @click="addUserDialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 编辑用户弹出框 -->
    <el-dialog title="添加用户" :visible.sync="editUserDialogVisible">
      <el-form ref="addUserForm" :model="editUserForm" label-width="120px">
        <el-form-item label="用户名">
          <el-input v-model="editUserForm.name"></el-input>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="editUserForm.isAuth" placeholder="请选择角色">
            <el-option label="Admin" value="1"></el-option>
            <el-option label="User" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="editUserForm.email"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="editUserForm.keynum" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleEditUserConfirm">确认</el-button>
          <el-button @click="editUserDialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


  </div>
</template>

<script>
import request from "../utils/request";

export default {
  data() {
    return {
      form: {
        title: '',
        mes: ''
      },
      users: [],
      announcement: [],
      // settingsForm: {
      //   param1: 'value1',
      //   param2: 'value2'
      // },
      detectionRecords: [],
      addUserDialogVisible: false,
      editUserDialogVisible: false,
      addUserForm: {
        name: '',
        isAuth: '',
        email: '',
        keynum: ''
      },
      editUserForm: {
        id:'',
        name: '',
        isAuth: '',
        email: '',
        keynum: ''
      }
    };
  },
   created() {
    this.load();
  },
  methods: {

    load(){
      this.form = {};
      request.get('/detection/list').then(res => {
        if(res.code === '200'){
          this.detectionRecords = res.data
        }
      })
      request.get("/user/list").then(res =>{
        this.users = res.data;
      })
      request.get("/announcement/list").then(res =>{
        this.announcement = res.data;
      })
    },

    // 发布系统消息
    confirmPostMessage() {
      this.$confirm('确定要发布这条消息吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handlePostMessage();
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消发布'
        });
      });
    },
    handlePostMessage() {
      request.post('/announcement/save', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('添加成功');
          this.load();
        } else {
          this.$notify.error(res.msg);
        }
      });
    },

    ///////////弹出添加用户窗口
    showAddUserDialog() {
      this.addUserDialogVisible = true;
    },
    //////////////////////// 添加用户
    handleAddUserConfirm() {
      request.post('/user/save', this.addUserForm).then(res => {
        if (res.code === '200') {
          this.$notify.success('添加成功');
          this.load();
        } else {
          this.$notify.error(res.msg);
        }
      });
      this.addUserDialogVisible = false;
    },
    /////////////////////////// 弹出编辑窗口
    handleEditUser(user) {
      // 编辑用户的逻辑
      request.get("/user/" + user.id).then(res =>{
        this.editUserForm = res.data;
        if(res.data.isAuth == 1){
          this.editUserForm.isAuth = "Admin";
        }else{
          this.editUserForm.isAuth = "User";
        }
        this.editUserDialogVisible = true;
      })
    },
    ///////////////////////// 编辑用户
    handleEditUserConfirm(){

      this.editUserDialogVisible = false;
      if(this.editUserForm.isAuth === "Admin"){
        this.editUserForm.isAuth = 1;
      }else if(this.editUserForm.isAuth === "User"){
        this.editUserForm.isAuth = 0;
      }
      request.put('/user/update', this.editUserForm).then(res => {
        if (res.code === '200') {
          this.$notify.success('添加成功');
          this.load();
        } else {
          this.$notify.error(res.msg);
        }
      });
    },
    /////////////////////////////// 删除用户
    handleDeleteUser(user) {
      request.delete("/user/delete/" + user.id).then(res => {
        if (res.code === '200') {
          this.$notify.success("删除成功");
          this.load(); // 调用刷新函数
        } else {
          this.$notify.error(res.msg);
        }
      });
    },


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
