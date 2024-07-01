<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-card class="box-card">
          <div class="user">
            <img :src= 'avatar' />
            <div class="userinfo">
              <p class="name">{{ user.name }}</p>
              <p class="access">ID: {{ user.cardnum }}</p>
            </div>
          </div>
          <div>
            <div class="login-info">
              <p>上次登录的时间:<span>{{ user.updatetime }}</span></p>
              <p>上次登录的地点:<span>广州</span></p>
            </div>
          </div>
        </el-card>

        <el-card style="margin-top: 20px">
          <div ref="echarts2" style="height: 300px;"></div>
        </el-card>
      </el-col>

      <el-col :span="16" style="padding-left: 10px">
        <el-card class="box-card system_bulletin" style="margin-bottom: 20px">
          <div slot="header" class="system_title">
            <span>系统公告</span>
          </div>
          <div v-for="item in systemData" :key="item.id" class="system_context item">
            <div>{{ item.title }}</div>
            <div><i :class="`el-icon-${item.icon}`"> {{ item.date }}</i></div>
          </div>
        </el-card>

        <el-card style="margin-top: 20px">
          <div class="system-performance">
            <h3>系统性能统计</h3>
            <div ref="echarts1" style="height: 300px;"></div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import Cookies from "js-cookie";
import request from "../../utils/request";
export default {
  name: 'FirstPage',
  data() {
    return {

      avatar: '@/assets/wife.png',
      user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},

      systemData: [
        {
          id: 1,
          date: "2024-6-23 12.00",
          title: "【维护通知】系统升级或维护时间",
          icon:'timer'
        },
        {
          id: 2,
          date: "2024-6-22 11.32",
          title: "【服务中断或故障】预计修复时间",
          icon:'timer'
        },
        {
          id: 3,
          date: "2024-6-21 10.00",
          title: "【内部分享】如何建立良好的客户关系",
          icon:'timer'
        },
        {
          id: 4,
          date: "2024-6-17 10.20",
          title: "【活动和促销】参与方式和时间",
          icon:'timer'
        },
        {
          id: 5,
          date: "2024-6-14 09:00",
          title: "【政策变更】用户协议或隐私政策的变更",
          icon:'timer'
        },
        {
          id: 6,
          date: "2024-6-12 13:00",
          title: "【重要通知】公司或平台的重要新闻",
          icon:'timer'
        }
      ]
    };
  },
  created() {
    this.load();
  },
  methods: {
    load(){
      request.get("/user/" + this.user.id).then(res =>{
        this.user = res.data;
        // console.log(this.form)
        if(this.user.cover == null){
          this.avatar = require('@/assets/second.png'); // default avatar
        }
        this.avatar = this.user.cover;
      })
    },

  },
  mounted() {
    var myChart1 = echarts.init(this.$refs.echarts1);
    var option1 = {
      title: {
        text: 'System Performance Statistics'
      },
      tooltip: {},
      xAxis: {
        data: ['Week 1', 'Week 2', 'Week 3', 'Week 4']
      },
      yAxis: {},
      series: [
        {
          name: 'Files Processed',
          type: 'bar',
          data: [150, 200, 180, 220]
        }
      ]
    };
    myChart1.setOption(option1);

    var myChart2 = echarts.init(this.$refs.echarts2);
    var option2 = {
      title: {
        text: 'Deepfake Detection Confidence'
      },
      tooltip: {
        trigger: 'axis'
      },
      xAxis: {
        type: 'category',
        data: ['Video 1', 'Video 2', 'Video 3', 'Video 4', 'Video 5']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: 'Confidence',
          type: 'line',
          data: [85, 70, 90, 60, 75],
          markPoint: {
            data: [
              { type: 'max', name: 'Max' },
              { type: 'min', name: 'Min' }
            ]
          },
          markLine: {
            data: [
              { type: 'average', name: 'Average' }
            ]
          }
        }
      ]
    };
    myChart2.setOption(option2);
  }
};
</script>

<style lang="less" scoped>
.user {
  display: flex;
  align-items: center;
  padding-bottom: 20px;
  border-bottom: 1px solid #ccc;
  margin-bottom: 20px;

  img {
    margin-left: 3px;
    width: 140px;
    height: 140px;
    border-radius: 50%;
  }

  .userinfo {
    margin-left: 30px;

    .name {
      font-size: 35px;
      margin-bottom: 10px;
    }

    .access {
      font-size: 10px;
      color: #999;
    }
  }
}

.login-info {
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;

    span {
      color: #666666;
      margin-left: 20px;
    }
  }
}

.system_bulletin {
  .system_title {
    font-size: 22px;
    color: #000000;
  }

  .system_context {
    padding-bottom: 10px;
    margin-bottom: 10px;
    border-bottom: 1px solid rgb(242, 242, 242);
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
}

.system-performance {
  margin-top: 20px;

  h3 {
    margin-bottom: 10px;
    color: #333;
  }
}
</style>
