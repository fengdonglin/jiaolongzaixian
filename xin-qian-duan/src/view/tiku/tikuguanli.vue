<template>
    <div>

      <el-tabs type="border-card">
        <el-tab-pane label="新增题库">
            <xinzengtiku></xinzengtiku>
        </el-tab-pane>
        <el-tab-pane label="题库列表">
            <tikuliebiao></tikuliebiao>
        </el-tab-pane>
      </el-tabs>

    </div>
</template>

<script>
  import xinzengtiku from './xinzengtiku.vue'  //新增题库组件
  import tikuliebiao from './tikuliebiao'      //题库列表组件

    export default {
        components:{
          xinzengtiku,  //注册新增题库组件
          tikuliebiao   //注册题库列表组件
        },
        name: "tikuguanli",

        data(){
          return{

          }
        },
        methods:{


        }
    }
</script>

<style scoped>

</style>
