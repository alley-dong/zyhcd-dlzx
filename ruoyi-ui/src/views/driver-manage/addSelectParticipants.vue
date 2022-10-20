<template>
    <div>
        <div style="width:98%; margin:0 auto; margin:20px 10px 0 0; overflow:hidden;">
            <el-button type="primary" style="float: right;margin-right: 10px;" @click="submitClick()">提交</el-button>
            <!-- <router-link :to="{path:'/addNotice',name: 'addNotice'}">  -->
                <el-button @click="backClick" type="primary" style="float: right;margin-right: 10px;">返回</el-button>
            <!-- </router-link> -->
        </div>
        <div class="wrap" style="margin-bottom:20px;">
            <div class="text">选择人员</div>
            <div class="line"></div>
        </div>
        <el-form :inline="true" class="demo-form-inline" :model="listQuery" style="width:90%; margin:0 auto; margin-top:20px">
            <el-form-item label="驾驶员姓名：" prop="name">
                <el-input v-model="listQuery.name" placeholder="请输入驾驶员姓名"></el-input>
            </el-form-item>
            <el-form-item label="联系方式：" prop="name">
                <el-input v-model="listQuery.contact" placeholder="请输入手机号"></el-input>
            </el-form-item>
            <el-button type="primary" style="float: right;margin-right: 10px;" @click="handleFilter">查询</el-button>
        </el-form>
        <el-table ref="table" border :data="tableData" tooltip-effect="dark" style="width: 90%; margin:0 auto; margin-top:20px; margin-bottom:30px;" :row-key="(row) => row.id" @selection-change="handleSelectionChange">
            <el-table-column type="selection" align="center" :reserve-selection="true"></el-table-column>
            <el-table-column label="姓名" align="center">
                <template slot-scope="scope">{{ scope.row.name }}</template>
            </el-table-column>
            <el-table-column label="性别" align="center">
               <template slot-scope="scope">
                    <span v-if="scope.row.sex == 0">男</span>
                    <span v-else-if="scope.row.sex == 1">女</span>
                    <span v-else>-</span>
                </template>
            </el-table-column>
            <el-table-column label="联系电话" align="center">
                <template slot-scope="scope">{{ scope.row.contact }}</template>
            </el-table-column>
            <el-table-column label="身份证" align="center">
                <template slot-scope="scope">{{ scope.row.idCard }}</template>
            </el-table-column>
        </el-table>

      <!--<pagination
        v-show="total > 0"
        :total="total"
        :page.sync="listQuery.pageNum"
        :limit.sync="listQuery.pageSize"
        @pagination="getList"
      />-->
    </div>
</template>

<script>
import {selectParticipantsList} from '@/api/driver/noticeManage'

export default {
    name: 'addSelectParticipants',
    data() {
        return {
            checkUsersId:[],
            listQuery: {
                // pageNum: 1,
                // pageSize: 10,
                name:'',
                contact:'',
                id:'',
            },
            tableData:[],
            list:[],
            listName:[],
          total: 0,
        }
    },
    created() {
        this.getList();
    },
    methods: {
        // 查询
        handleFilter() {
            this.getList()
        },
        // 打勾获取
        handleSelectionChange(val) {
            console.log(val);
            let arr  = []
            let arrName = []
            for (const item of val) {
                arr.push(item.id)
                arrName.push(item.name)
            }
            this.list= arr;
            this.listName= arrName;
        },
        // 获取table的表格数据
        getList() {
            this.listLoading = true
            selectParticipantsList(this.listQuery).then(response => {
                console.log(response)
                this.total = response.total
                this.tableData = response.rows
                this.listLoading = false
            }).catch(err => {
                this.listLoading = false
                console.log(err)
            }).then(()=>{
              if (localStorage.getItem("myAddNoticeDataList")) {
                this.checkUsersId = localStorage.getItem("myAddNoticeDataList");
                for(let i=0;i<this.tableData.length;i++){
                  if(this.checkUsersId.indexOf(this.tableData[i].id) >= 0){
                    this.$refs.table.toggleRowSelection(this.tableData[i],true);
                  }
                }
              }
            })
        },
        backClick(){
            this.$router.go(-1)
        },
        // 点击提交按钮
        submitClick() {
            this.$message({
              message: '提交成功',
              type: 'success'
            })

            if(!this.list){
              localStorage.removeItem('myAddNoticeDataList')
              localStorage.removeItem('myAddNoticeDataNameList')
            } else {
              localStorage.setItem('myAddNoticeDataList',this.list)//query: {id: this.listQuery.id }
              localStorage.setItem('myAddNoticeDataNameList',this.listName)//query: {id: this.listQuery.id }
            }
            this.$router.go(-1)
        }
    }
}
</script>

<style scoped>
    .wrap {
        width: 100%;
        height: 40px;
        position: relative;
    }
    .text{
        width: 10%;
        line-height: 40px;
        text-align: center;
        font-size: 16px;
        color: black;
        float: left;
        font-weight: 400;
    }
    .line {
        width: 90%;
        border-bottom: 3px solid #333399;
        position: absolute;
        left:10%;
        top: 50%;
        z-index: 1;
        float: left;
    }
</style>
