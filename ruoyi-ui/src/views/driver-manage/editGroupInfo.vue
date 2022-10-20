<template>
    <div>
        <div class="wrap" style="margin:20px 0 10px 0;">
            <div class="text">编辑</div>
            <div class="line"></div>
        </div>
        <el-form
        :inline="true"
        class="demo-form-inline"
        :model="listQuery"
        style="width:90%; margin:0 auto; margin-top:30px"
        >
        <el-form-item
            label="群组名称："
            prop="name"
            :rules="[
                { required: true, message: '群组名称为必填项'}
            ]">
            <el-input v-model="listQuery.name" placeholder="请输入群组名称"></el-input>
        </el-form-item>
        <el-button type="primary" style="float: right;margin-right: 10px;" @click="saveClick()">保存</el-button>
        <el-button type="primary" style="float: right;margin-right: 10px;" @click="$router.go(-1)">返回</el-button>
    </el-form>
    <el-table
      ref="multipleTable"
      border
      :data="tableData"
      tooltip-effect="dark"
      style="width: 90%; margin:0 auto; margin-bottom:30px;"
      @selection-change="handleSelectionChange">
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
import {
  addGroupList,
  getgroupManageDetails,
  editGroup,
  groupManageList1
} from "@/api/driver/groupManage";
import {
  dDriverGroupA39GetInfo,
} from "@/api/driver/paperManage";
export default {
  name: "editGroupInfo",
  data() {
    return {
      tableData: [],
      checkData: [],
      listQuery: {
        name: "",
        driver: []
      }
    };
  },
  created() {
    this.getParams();
    this.getList();
    this.groupManageListData();
  },

  methods: {
    // 获取数据
    getParams() {
      //取到路由带过来的参数
      this.id = this.$route.query.id;
      var getId = this.id;
      getgroupManageDetails(getId)
        .then(response => {
          console.log(response);
          this.listQuery = response.data;
      this.listQuery.id = this.$route.query.id;
        })
        .catch(err => {
          console.log(err);
        });
    },
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
    // 编辑选中
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    groupManageListData() {
      groupManageList1(this.$route.query.id).then(res => {
        let arr = []
        for (const item of res.data.driver) {
          //1.遍历取得 选中的数组的元素
          // 2.找到选中的数组并取出
          let obj = this.tableData.find(_ => _.id == item.driverId);
          // 3. 将元素添加到新数组
          if (obj) {
            arr.push(obj);
          }
        }
        console.log(this.checkData);
        this.toggleSelection(arr)
      });
    },
    // 获取table的表格数据
    getList() {
      addGroupList(this.listQuery)
        .then(response => {
          console.log(response);
          this.total = response.total;
          this.tableData = response.rows;
          this.listLoading = false;
          // this.dExamPaperListDriverData()
        })
        .catch(err => {
          this.listLoading = false;
          console.log(err);
        });
    },
     // 选中list
    dExamPaperListDriverData() {
      dDriverGroupA39GetInfo(this.$route.query.id).then((res) => {
        // console.log(res.driverId);
        let arr = [];
        for (const item of res.data.driver) {
          console.log(this.tableData);
          let obj = this.tableData.find((_) => _.id == item.driverId);
          /* 
          
            for (const data of this.tableData) {
            console.log(data.id , item.driverId);
            if (data.id == item.driverId) {
              arr.push(obj);
            }
          }*/
          // if (obj.id == item.id) {
            console.log(obj);
           obj? arr.push(obj):null;
          // }
        }
        this.checkList = arr
        this.toggleSelection(arr);
      });
    },
    toggleSelection(rows) {
      console.log(rows);
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    //点击保存
    saveClick() {
      editGroup(this.listQuery)
        .then(response => {
          this.$message({
            message: "保存成功",
            type: "success"
          });
          setTimeout(() => {
            // this.$router.push({ path: "/groupManage", name: "groupManage" });
            this.$router.go(-1);
          }, 1000);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
}
.text {
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
  left: 5%;
  top: 50%;
  z-index: 1;
  float: left;
}
</style>