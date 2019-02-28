<template>
    <div
      v-loading="loading2"
      element-loading-text="拼命加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      :style="{height:wheight+'px'}">  <!--wheight 要动态给他赋值-->


      <el-form ref="form" :model="form" label-width="80px" :style="{width:'50%'}">
        <el-form-item label="题库名称">
          <el-input v-model="form.tikuname"></el-input>
        </el-form-item>
        <el-form-item label="开放状态">
          <el-select v-model="form.tikuzhuangtai" placeholder="请选择开放状态">
            <el-option label="开放" value="1"></el-option>
            <el-option label="关闭" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="题库说明">
          <el-input type="textarea" v-model="form.tikushuoming"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">保存</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>

    </div>
</template>

<script>
    export default {
        name: "xinzengtiku",
        data(){
          return{
            loading2:false,
            form:{},     //初始化表单对象
            wheight:window.innerHeight-132  //获取窗口可视高度
          }
        },
        methods:{
          onSubmit(){ //提交表单数据的事件
             //打开加载提示窗口
             this.$data.loading2=true
             let tikudata=this.$data.form
             this.$axios.post(this.domain.serverpath+"tiku/toaddTiKuInfo",JSON.stringify(tikudata)).then((response)=>{
                  if(response.data.success=="ok"){
                    this.$message({
                      message: '添加成功！',
                      type: 'success',
                      duration:2000,
                    });
                    //关闭加载窗口
                    this.$data.loading2=false
                    //清空表单
                    this.$data.form={}
                  }else{
                    this.$message({
                      message: '错误！',
                      type: 'error',
                      duration:2000
                    });
                  }
             })
          }
        }


    }
</script>

<style scoped>

</style>
