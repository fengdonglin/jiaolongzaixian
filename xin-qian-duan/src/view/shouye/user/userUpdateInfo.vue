<template>
    <div>

      <!-- 图片上传      :auto-upload是否自动上传 -->
      <el-upload
        ref="uploadvip"
        action="http://localhost:8888/user/uploadVip"
        name="file"
        list-type="picture-card"
        :auto-upload="false"
        :limit=2
        :file-list="filelistVip"
        :multiple="true"
        :before-upload="beforeUploadVip"
        :data="mydata"
        >
        <i class="el-icon-plus"></i>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>

      <br>
      <br>


      <el-form :model="ruleForm2" :inline="true" ref="ruleForm2" class="demo-ruleForm">

        <el-form-item label="用户名" prop="userName">
          <el-input v-model="ruleForm2.userName"></el-input>
        </el-form-item>
        <el-form-item label="登录名" prop="loginName">
          <el-input v-model="ruleForm2.loginName"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitFormVip('ruleForm2')">提交</el-button>
          <el-button @click="resetForm('ruleForm2')">重置</el-button>
        </el-form-item>
      </el-form>


    </div>
</template>

<script>
    export default {
        name: "userUpdateInfo",

      data(){
          return{
              ruleForm2:{
                userName:'',
                loginName:''
              },
              mydata:{     //这是一个数据对象

              },
              filelistVip:[]

          }
      },
      methods:{
        beforeUploadVip(file){   //上传文件之前的钩子，参数为上传的文件    限制上传文件的格式和大小
          //规定图片的大小、格式
          const isJPG = file.type === 'image/jpeg';     //图片类型
          const isLt2M = file.size / 1024 / 1024 < 2;   //图片大小

          if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG 格式!');
          }
          if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
          }
          return isJPG && isLt2M;
        },
        submitFormVip(){
            let dataObj= this.$data.ruleForm2
           //获取表单中的数据
           this.$data.mydata.obj = JSON.stringify(dataObj)

           this.$refs.uploadvip.submit()   //手动上传文件列表
        },
        toSetData(row){   //给子页面赋值的方法，也就是回显数据     row是父组件传过来的数据     数据传过来以后直接给ruleForm2表单赋值

          this.$data.ruleForm2=row   //回显数据


          let ff = {}
          ff.name=row.userName
          ff.url="https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg"
          this.$data.filelistVip=[]
          this.$data.filelistVip.push(ff)
        }
      }
    }
</script>

<style scoped>

</style>
