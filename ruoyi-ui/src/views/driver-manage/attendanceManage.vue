<template>
    <div>
        <el-form :inline="true" class="demo-form-inline" :model="listQuery" style="width:98%; margin:0 auto; margin-top:30px">
            <el-form-item label="驾驶员姓名：">
                <el-input v-model="listQuery.driverName" placeholder="请输入驾驶员姓名" />
            </el-form-item>
            <el-form-item label="审批状态：">
                <el-select v-model="listQuery.status" placeholder="请选择审批状态">
                    <el-option
                        v-for="item in status"
                        :key="item.value"
                        :value="item.value"
                        :label="item.label">
                    </el-option>
                </el-select>
            </el-form-item>
            <!-- <router-link :to="{path:'/leaveStatistics',name: 'leaveStatistics'}"> 
                <el-button style="float: right; margin:0 10px 20px 0">请假统计</el-button>
            </router-link>
            <el-button style="float: right; margin:0 10px 20px 0" @click="handleFilter">查询</el-button> -->
             <buttonGroup
        @handleFilter="handleFilter"
        @handleBack="$router.push({path:'/leaveStatistics'})"
        twoButtonState
        style="float: right"
        backButton
          :addHasPermi="['pfserverperson:vacation:checkinlist']"
        backButtonState='请假统计'
      />
        </el-form>
        <el-table v-loading="listLoading" border :data="tableData" style="width: 98%; margin:0 auto;">
            <el-table-column label="驾驶员姓名" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.driverName }}</span>
                </template>
            </el-table-column>
            <el-table-column label="请假类型" align="center">
                <template slot-scope="scope">
                  {{scope.row.typeEnum}}
                </template>
            </el-table-column>
            <el-table-column label="开始时间" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.vStartTime | data }}</span>
                </template>
            </el-table-column>
            <el-table-column label="结束时间" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.vEndTime | data }}</span>
                </template>
            </el-table-column>
            <el-table-column label="请假时长(天)" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.duration }}</span>
                </template>
            </el-table-column>
            <el-table-column label="申请时间" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.createTime | dataTime }}</span>
                </template>
            </el-table-column>
            <el-table-column label="审批状态" align="center">
                <template slot-scope="scope">
                    <span v-if="scope.row.status === 1">待审核</span>
                    <span v-else-if="scope.row.status === 2">已通过</span>
                    <span v-else-if="scope.row.status === 3">已拒绝</span>
                    <span v-else>-</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="250px">
                <template slot-scope="scope">
                    <el-button type="primary" plain v-if="scope.row.status === 1" @click="approvalClick(scope.row)" style="margin-right:15px;"
            v-hasPermi="['pfserverperson:vacation:check']"
                    >审批</el-button>
                    <el-button type="primary" v-if="scope.row.status === 2 || scope.row.status === 3" @click="checkClick(scope.row)" style="margin-right:15px;"
            v-hasPermi="['pfserverperson:vacation:info']"
                    >查看</el-button>
                </template>
            </el-table-column>
        </el-table>
        <pagination
            v-show="total>0"
            :total="total"
            :page.sync="listQuery.pageNum"
            :limit.sync="listQuery.pageSize"
            @pagination="getList"
        />
    </div>
</template>

<script>
import {attendanceManageList} from '@/api/driver/attendanceManage'
import buttonGroup from "@/views/components/com/buttonGroup";

export default {
    name: 'attendanceManage',
    components: {
    buttonGroup,
  },
    data() {
        return {
            total:10,
            listLoading:false,
            tableData:[],
            listQuery: {
                pageNum:1,
                pageSize:10,
                driverName:'',
                status:null
            },
            status:[{
                value: '1',
                label: '待审批'
            },{
                value: '2',
                label: '已通过'
            },{
                value: '3',
                label: '已拒绝'
            },{
                value:null,
                label:'全部'
            }]
        }
    },
    //格式化时间戳
    filters: {
        data(input) {
            const d = new Date(input);
            const year = d.getFullYear();
            const month = d.getMonth() + 1;
            const day = (d.getDate() < 10 ? '0' : '') + d.getDate();
            return year + '-' + month + '-' + day;
        },
        dataTime(input) {
            const d = new Date(input);
            const year = d.getFullYear();
            const month = d.getMonth() + 1 < 10 ? '0' + (d.getMonth() + 1) : d.getMonth() + 1
            const day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate()
            const hours = d.getHours() < 10 ? '0' + d.getHours() : d.getHours()
            const min = d.getMinutes() < 10 ? '0' + d.getMinutes() : d.getMinutes()
            return year + '-' + month + '-' + day + ' ' + hours + ':' + min;
        },
    },
    created() {
        this.getList();
    },
    methods: {
        // 查询
        handleFilter() {
            this.listQuery.pageNum = 1
            this.getList()
        },
        // 获取table的表格数据
        getList() {
            console.log('.11111是否被调用' )
            this.listLoading = true
            attendanceManageList(this.listQuery).then(response => {
                console.log(response)
                this.total = response.total
                this.tableData = response.rows
                this.listLoading = false
            }).catch(err => {
                this.listLoading = false
                console.log(err)
            })
        },
        // 点击审批按钮
        approvalClick(row) {
            this.$router.push({path:'/attendanceManageApproval',query: {id: row.id}})
        },
        // 点击查看按钮
        checkClick(row) {
            this.$router.push({path:'/attendanceManageCheck',query: {id: row.id}})
        } 
    }
}
</script>

<style scoped>

</style>