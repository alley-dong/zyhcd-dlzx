<template>
    <div>
        <el-form :inline="true" class="demo-form-inline" :model="listQuery" style="width:98%; margin:0 auto;margin-top:30px;">
            <el-form-item label="姓名：">
                <el-input v-model="listQuery.driverName" placeholder="请输入姓名" />
            </el-form-item>
            <el-form-item label="联系电话：">
                <el-input v-model="listQuery.tel" placeholder="请输入联系电话" />
            </el-form-item>
            <el-form-item label="状态：">
                <el-select v-model="listQuery.status" placeholder="状态">
                    <el-option
                        v-for="item in status"
                        :key="item.value"
                        :value="item.value"
                        :label="item.label">
                    </el-option>
                </el-select>
            </el-form-item>
                <el-button @click="$router.go(-1)" type="primary" style="float: right; margin:0 10px 20px 0;">返回</el-button>
            <el-button type="primary" style="float: right; margin:0 10px 20px 0;" @click="handleFilter">查询</el-button>
        </el-form>
        <el-table border :data="tableData" style="width: 98%; margin:0 auto;">
            <el-table-column label="姓名" align="center">
                <template slot-scope="scope">
                <span>{{ scope.row.driverName }}</span>
                </template>
            </el-table-column>
            <el-table-column label="联系电话" align="center">
                <template slot-scope="scope">
                <span>{{ scope.row.tel }}</span>
                </template>
            </el-table-column>
            <el-table-column label="身份证" align="center">
                <template slot-scope="scope">
                <span>{{ scope.row.idCard }}</span>
                </template>
            </el-table-column>
            <el-table-column label="状态" align="center">
                <template slot-scope="scope">
                    <span v-if="scope.row.status == 1">未读</span>
                    <span v-else-if="scope.row.status == 2">已读</span>
                    <span v-else>-</span>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import {getReadedListDetails} from '@/api/driver/noticeManage'

export default {
    name:'readedList',
    data() {
        return {
            status:[{
                value:1,
                label:'未读'
            },{
                value:2,
                label:'已读'
            },{
                value:null,
                label:'全部'
            }],
            listQuery: {
                driverName:'',
                tel:'',
                status:null,
                noticeId:''
            },
            tableData:[]
        }
    },
    created() {
        this.getParams();
    },
    methods: {
        // 查询
        handleFilter() {
            this.getParams()
        },
        // 获取数据
        getParams() {
            this.listQuery.noticeId = this.$route.query.id;
            getReadedListDetails(this.listQuery).then(response => {
                console.log(response)
                this.tableData = response.rows
            }).catch(err => {
                console.log(err);
            })
        }
    },
    watch: {
        '$route': 'getParams'
    }
}
</script>

<style scoped>

</style>