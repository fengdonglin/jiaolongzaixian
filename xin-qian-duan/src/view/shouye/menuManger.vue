<template>
   <div >
     <p>菜单列表</p>
     <el-input :style="{width:'50%'}"
       placeholder="输入关键字进行过滤"
       v-model="filterText">
     </el-input>

     <el-tree
       class="filter-tree"
       :data="data2"
       :props="defaultProps"
       default-expand-all
       show-checkbox
       :filter-node-method="filterNode"
       ref="tree2"
       @node-click="nodeClick"
       @node-contextmenu="youjishijian"
       >
     </el-tree>

     <el-dialog
       :title="menuInfo"
       :visible.sync="dialogVisible"
       width="30%"
       >
       <el-row style="border-bottom: darkgrey 1px solid;margin-bottom:10px;">
       <el-tooltip class="item" effect="dark" content="点击添加菜单" placement="top-start">
         <el-button icon="el-icon-plus" circle @click="add" ></el-button>
       </el-tooltip>
       <el-tooltip class="item" effect="dark" content="点击更新当前信息" placement="top-start">
         <el-button type="primary" icon="el-icon-edit" circle @click="update2" ></el-button>
       </el-tooltip>
       <el-tooltip class="item" effect="dark" content="删除当前菜单" placement="top-start">
         <el-button type="danger" icon="el-icon-delete" circle ></el-button>
       </el-tooltip>

       </el-row>

       <el-form ref="add" v-show="form1.form1show" :inline="true" v-model="form1" class="demo-form-inline">
          <i class="el-icon-plus" style="margin-top:10px" >添加菜单</i>
          <br>
          <br>
         <el-form-item label="当前菜单等级" >
           <el-input v-model="form1.parentLeval" :style="{'margin-left':15+'px',width:200+'px'}" :readonly="true"></el-input>
         </el-form-item>
         <el-form-item label="当前菜单名称" >
           <el-input v-model="form1.currMenuName"  maxlength="10"  :readonly="true"></el-input>
         </el-form-item>
         <el-form-item label="当前菜单ID" >
           <el-input v-model="form1.parentId" :style="{'margin-left':29+'px',width:200+'px'}"  :readonly="true"></el-input>
         </el-form-item>
         <el-form-item label="添加子菜单名称">
           <el-input v-model="form1.zicaidanName" :style="{width:200+'px'}" ></el-input>
         </el-form-item>
         <el-form-item label="子菜单访问的URL">
           <el-input v-model="form1.zicaidanURL" :style="{width:200+'px'}" ></el-input>
         </el-form-item>


         <el-form-item>
           <el-button type="primary" @click="saveMenu()">保存</el-button>
           <el-button>取消</el-button>
         </el-form-item>
       </el-form>

       <el-form ref="update" v-show="form2.form2show" :inline="true" v-model="form2" class="demo-form-inline">
         <i class="el-icon-edit">修改菜单信息</i>
         <br>
         <br>
         <el-form-item label="当前菜单等级">
           <el-input v-model="form2.dangqiandengji" :style="{width:200+'px'}" :readonly="true"></el-input>
         </el-form-item>
         <el-form-item label="当前菜单ID">
           <el-input v-model="form2.dangqianId" :style="{'margin-left':14+'px',width:200+'px'}" :readonly="true"></el-input>
         </el-form-item>
         <el-form-item label="当前菜单的父ID">
           <el-input v-model="form2.parentId" :style="{width:200+'px'}" :readonly="true"></el-input>
         </el-form-item>
         <el-form-item label="当前菜单名称">
           <el-input v-model="form2.dangqianName" :style="{'margin-left':14+'px',width:200+'px'}"></el-input>
         </el-form-item>
         <el-form-item label="当前菜单访问的URL">
           <el-input v-model="form2.dangqianURL" :style="{width:200+'px'}"></el-input>
         </el-form-item>


         <el-form-item>
           <el-button type="primary" @click="updata">保存修改</el-button>
           <el-button>取消</el-button>
         </el-form-item>

       </el-form>

       <!--<span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
       </span>-->
   </el-dialog>


   </div>
</template>

<script>
    export default {
        name: "menuManger",
        data() {
          return {
            form1:{
              parentLeval:'',
              parentId:'',
              currMenuName:'',
              form1show:false,
              zicaidanName:'',
              zicaidanURL:''
            },
            form2:{
              dangqiandengji:'',
              dangqianId:'',
              parentId:'',
              dangqianName:'',
              dangqianURL:'',
              form2show:false,
            },
            parent:{
               id:'',
               leval:''
            },
            menuInfo:"",
            dialogVisible: false,
            filterText: '',
            data2: [],
            defaultProps: {
              id:'id',
              children: 'listMenu',
              label: 'label',
              leval:'leval',
              parentMenuId:'parentMenuId',
            }
          }
        },
        watch: {
          filterText(val) {
            this.$refs.tree2.filter(val);
          }
        },
        methods:{
          filterNode(value, data) {
            if (!value) return true;
            return data.label.indexOf(value) !== -1;
          },
          youjishijian(data){

          },
          nodeClick(node){

              //弹出层
              this.$data.dialogVisible=true;
              this.$data.form2.form2show=false;
              this.$data.form1.form1show=false;

              this.$data.menuInfo="当前操作的菜单名称-"+node.label;
              this.$data.form1.parentId=node.id;
              this.$data.form1.parentLeval=node.leval;
              this.$data.form1.currMenuName=node.menuName;

              this.$data.form2.parentId=node.parentMenuId;
              this.$data.form2.dangqianId=node.id;
              this.$data.form2.dangqianURL=node.url;
              this.$data.form2.dangqiandengji=node.leval;
              this.$data.form2.dangqianName=node.menuName;


          },
          add(){
             if(this.$data.form2.form2show){
               this.$data.form2.form2show=false;
             }
             if(!this.$data.form1.form1show){
               this.$data.form1.form1show=true;
             }
          },
          update2(){
            if(this.$data.form1.form1show){
              this.$data.form1.form1show=false;
            }
            if(!this.$data.form2.form2show){
              this.$data.form2.form2show=true;
            }


          },
          saveMenu(){
             //保存新增加的菜单数据
             let form1= this.$data.form1;
             //获取查询参数
             let qs=require("qs");
             this.$axios.post(this.domain.serverpath+"menu/addMenu",qs.stringify(form1)).then((response)=>{
               this.$data.dialogVisible=false
               this.$axios.post(this.domain.serverpath+"menu/treeMenu").then((reponse)=>{
                 //为data中的listMenu赋值
                 this.data2=reponse.data.result;

               }).catch((error)=>{
                 console.log("===error==="+error.message);
               })
             }).catch((error)=>{

             })
          },

          //修改
          updata(){
             let qs=require("qs");
             this.$axios.post(this.domain.serverpath+"",qs.stringify(this.$data.form2)).then((response)=>{

             })
          }
        },
        mounted(){
          this.$axios.post(this.domain.serverpath+"menu/treeMenu").then((reponse)=>{
            //为data中的listMenu赋值
            this.data2=reponse.data.result;

          }).catch((error)=>{
            console.log("===error==="+error.message);

          })
        }
    }
</script>

<style scoped>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }

</style>
