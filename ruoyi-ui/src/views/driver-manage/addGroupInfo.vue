<template>
    <div>
        <div class="wrap" style="margin:20px 0 10px 0;">
            <div class="text">新增</div>
            <div class="line"></div>
        </div>
        <el-form :inline="true" ref="listQuery" class="demo-form-inline" :model="listQuery" style="width:90%; margin:0 auto; margin-top:30px">
            <el-form-item label="群组名称：" prop="name" 
            :rules="[
                { required: true, message: '群组名称为必填项'}
            ]">
                <el-input v-model="listQuery.name" placeholder="请输入群组名称"></el-input>
            </el-form-item>
            <el-button type="primary" style="float: right;margin-right: 10px;" @click="saveClick()">保存</el-button>
            <!-- <router-link :to="{path:'/groupManage',name: 'groupManage'}">  -->
                <el-button type="primary" @click="$router.go(-1)" style="float: right;margin-right: 10px;">返回</el-button>
            <!-- </router-link> -->
        </el-form>
        <el-table ref="multipleTable" border :data="tableData" tooltip-effect="dark" style="width: 90%; margin:0 auto; margin-bottom:30px;" @selection-change="handleSelectionChange">
            <el-table-column type="selection" align="center"></el-table-column>
            <el-table-column label="序号" type="index" width="50" align="center"></el-table-column>
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
    </div>
</template>

<script>
import {addGroupList, addGroup} from '@/api/driver/groupManage'
export default {
    name: 'addGroupInfo',
    data() {
        return {
            tableData:[],
            listQuery: {
                name:'',
                driver: []
            }
        }
    },
    created() {
        this.getList();
    },
    methods: {
        // 打勾获取
        handleSelectionChange(val) {
            console.log(val);
            let arr = []
            for (const item of val) {
                let obj = {}
                obj.driverSex = item.sex
                obj.driverName = item.name
                obj.tel = item.contact
                obj.driverId = item.id
                arr.push(obj)
            }
            this.listQuery.driver = arr;
        },
        // 获取table的表格数据
        getList() {
            this.listLoading = true
            addGroupList(this.listQuery).then(response => {
                console.log(response)
                this.total = response.total
                this.tableData = response.rows
                this.listLoading = false
            }).catch(err => {
                this.listLoading = false
                console.log(err)
            })
        },
        //点击保存
        saveClick() {

            addGroup(this.listQuery).then(response => {
                this.$message({
                    message: '保存成功',
                    type: 'success'
                })
                this.$refs['listQuery'].resetFields()
                this.toggleSelection();
                setTimeout(() => {
                    this.$router.go(-1)
                    // this.$router.push({path:'/groupManage',name:'groupManage'});
                },1000)
            }).catch(err => {
                console.log(err)
            })
        },
        // 取消勾选
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
      },
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
        width: 5%;
        line-height: 40px;
        text-align: center;
        font-size: 16px;
        color: black;
        float: left;
        font-weight: 400;
    }
    .line {
        width: 95%;
        border-bottom: 3px solid #333399;
        position: absolute;
        left:5%;
        top: 50%;
        z-index: 1;
        float: left;
    }
</style>