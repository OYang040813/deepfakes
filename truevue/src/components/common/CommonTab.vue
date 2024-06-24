<template>
  <div class="tabs">
    <el-tag
      v-for="(item,index) in tags"
      :key="item.path"
      :closable="item.name !== 'FirstPage'"
      :effect="$route.name === item.name ? 'dark':'plain'"
      @click="changeMenu(item)"
      @close="handleClose(item,index)"
    >
      {{ item.label }}
    </el-tag>
  </div>
</template>
<script>
import { mapState,mapMutations } from "vuex";
export default {
  name: "CommonTag",
  data() {
    return {};
  },
  computed: {
    ...mapState({
      tags: (state) => state.tab.tabsList,
    })
  },methods:{
    ...mapMutations(['closeTag']),
    changeMenu(item){
      this.$router.push(item.path)
    },handleClose(item,index){
      this.closeTag(item)
      const length = this.tags.length - 1 ;
//this.$router.push({name:this.tags[length].name})
      if(item.name !== this.$route.name){
        return
      }
      this.$router.push({name:this.tags[0].name})
    }
  }
};
</script>
<style lang="less" scoped>
.tabs{
  position: relative;
  //height: 30px;
  overflow: hidden;
  background: #fff;
  //padding-right: 120px;
  box-shadow: 0 5px 10px #ddd;
  padding-bottom: 5px;
  padding-top: 5px;
  padding-left: 5px;
  .el-tag{
    margin-right: 15px;
    cursor:pointer
  }
}
</style>
