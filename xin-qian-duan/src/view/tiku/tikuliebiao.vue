<template>
    <div>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column
          label="题库名称"
          prop="tikuname"
          width="180">
        </el-table-column>
        <el-table-column
          label="状态"
          prop="tikuzhuangtai"
          width="180">
        </el-table-column>
        <el-table-column
          label="说明"
          prop="tikushuoming"
          width="180">
        </el-table-column>
        <el-table-column
          label="创建时间"
          prop="createtime"
          width="180">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--引入分页组件-->
      <pageShow ref="pageShow"></pageShow>
    </div>
</template>

<script>
  //导入分页组件
  import pageShow from '../common/pagefoot.vue'

    export default {
        name: "tikuliebiao",

        components:{
          pageShow
        },
        data(){
          return{
            tableData:[]  //展示

          }
        },

        methods:{
          handleDelete(row){
            alert(JSON.stringify(row))
            //向后台发送

          }
        },

        //钩子函数  作用初始化页面
        mounted(){

          //初始化数据                                                      response后台响应回来的数据
          this.$axios.post(this.domain.serverpath+"tiku/getTikuList").then((response)=>{
               //将查询到的数据赋给表格
               this.$data.tableData=response.data.result.resultList
               //数据的总记录条数
               this.$refs.pageShow.total=response.data.result.totalCount
          })
        }
    }
</script>

<style scoped>

</style>
