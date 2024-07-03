import axios from "axios";
import router from "../router";
import Cookies from "js-cookie";

const request = axios.create({
  baseURL:'http://10.195.154.158:9090/api',  //全局统一加上后台基本前缀，所有接口前面都会有后台的基本地址
  timeout : 5000
})

//request 拦截器
//可以在请求发送前对请求做一些处理
//比如统一加token，对请求参数统一加密
request.interceptors.request.use(config =>{
  config.headers['Content-Type'] = 'application/json;charset=utf-8';

  const userJson = Cookies.get('user')
  if(userJson){
    //设置请求头
    config.headers['token'] = JSON.parse(userJson).token
  }

  return config
}, error => {
  return Promise.reject(error)
});

//request 拦截器
//可以在接口响应后统一处理结果
request.interceptors.response.use(
  response =>{
    let res = response.data; //此时res包含code、data、msg三个属性。
    //兼容服务器返回的字符串数据
    if(typeof res === 'string' ){
      res = res ? JSON.parse(res) : res
    }
    if (res.code === '401'){
      router.push('/login')
    }
    return res;
  },
  error => {
    console.log('err' + error) //for debug
    return Promise.reject(error)
  }
)


export default request
