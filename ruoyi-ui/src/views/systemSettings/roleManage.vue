<template>
    <div>
        <el-form :inline="true" class="demo-form-inline" :model="listQuery" style="margin-top:30px;">
            <el-form-item label="角色名称：">
                <el-input v-model="listQuery.roleName" placeholder="请输入账号" />
            </el-form-item>
            <el-form-item label="状态：">
                <el-select v-model="listQuery.status" clearable placeholder="状态">
                    <el-option
                        v-for="item in status"
                        :key="item.value"
                        :value="item.value"
                        :label="item.label">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="创建时间：">
                <el-date-picker type="date" placeholder="选择日期" v-model="listQuery.createTime"></el-date-picker>
            </el-form-item>
            <el-button type="primary" style="float: right;margin:0 20px 20px 0;">新增</el-button>
            <el-button type="primary" style="float: right;margin:0 20px 20px 0;" @click="handleFilter">查询</el-button>
        </el-form>
        <el-table v-loading="listLoading" border :data="tableData" style="width: 100%;">
            <el-table-column type="index" :index="typeIndex" label="序号" align="center" width="80px"></el-table-column>
            <el-table-column label="角色名称" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.roleName }}</span>
                </template>
            </el-table-column>
            <el-table-column label="状态" align="center" width="100px">
               <template slot-scope="scope">
                    <el-row>
						<el-col :span="24">
							<el-switch v-model="scope.row.status" active-color="#13ce66" inactive-color="#ccc" active-value="0"
							 inactive-value="1" @change="handleSwitch(scope.$index,scope.row)" />
						</el-col>
					</el-row>
                </template>
            </el-table-column>
            <el-table-column label="创建时间" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.createTime | data }}</span>
                </template>z
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="300px">
                <template slot-scope="scope">
                    <el-button size="small" type="primary">编辑</el-button>
                    <el-button size="small" type="danger" style="margin-left:20px;" @click="deleteClick(scope.row)">删除</el-button>
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
import {roleManageList, delUser} from '@/api/systemSettings/roleManage'

export default {
    name: 'roleManage',
    data() {
        return {
            total:10,
            listLoading:false,
            tableData:[],
            listQuery: {
                pageNum:1,
                pageSize:10,
                roleName:'',
                status:null,
                createTime:'2020-12-01'
            },
            status:[{
                value: '0',
                label: '启动'
            },{
                value: '1',
                label: '禁用'
            }]
        }
    },
    created() {
        this.getList();
    },
    //格式化时间戳
    filters: {
        data(input) {
            const d = new Date(input);
            const year = d.getFullYear();
            const month = d.getMonth() + 1 < 10 ? '0' + (d.getMonth() + 1) : d.getMonth() + 1
            const day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate()
            const hours = d.getHours() < 10 ? '0' + d.getHours() : d.getHours()
            const min = d.getMinutes() < 10 ? '0' + d.getMinutes() : d.getMinutes()
            const sec = d.getSeconds() < 10 ? '0' + d.getSeconds() : d.getSeconds()
            return year + '-' + month + '-' + day + ' ' + hours + ':' + min + ':' + sec;
        },
    },
    methods: {
        //切换状态按钮
        handleSwitch(index, row) {
            const param = {	
				id: row.id,
				status: row.status
			}
			this.changeStatus(param)//更新状态的接口
				.then(res => {
				    console.log(res)
					this.getList()
				})
				.catch(() => {})
		},
        //显示序号
        typeIndex(index) {
            return (this.listQuery.pageNum - 1) * this.listQuery.pageSize + index + 1;
        },
        // 查询
        handleFilter() {
            this.listQuery.pageNum = 1
            this.getList()
        },
        // 获取table的表格数据
        getList() {
            console.log('.11111是否被调用' )
            this.listLoading = true
            roleManageList(this.listQuery).then(response => {
                console.log(response)
                this.total = response.total
                this.tableData = response.rows
                this.listLoading = false
            }).catch(err => {
                this.listLoading = false
                console.log(err)
            })
        },
        //删除
        deleteClick(row) {
            const roleIds = row.roleId || this.ids;
            this.$confirm('是否确认删除用户编号为"' + roleIds + '"的数据项?', "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
                }).then(function() {
                    return delUser(roleIds);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                })
        },
    }
}
</script>

<style scoped>

</style>