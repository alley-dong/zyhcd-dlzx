<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="resetForm">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">新增</div>
      <div class="line"></div>
    </div>
    <div style="display: flex; margin-top: 30px">
      <el-row :gutter="15" style="width: 70%">
        <el-form
          ref="form"
          :model="form"
          :rules="rules"
          size="medium"
          label-width="121px"
        >
          <el-col :span="24">
            <el-form-item
              label="定点名称"
              prop="createBy"
              style="display: inline-block"
            >
              {{ form.name }}
            </el-form-item></el-col
          >
          <el-col :span="24"
            ><el-form-item
              label="线路"
              prop="etcCardNumber"
              style="display: inline-block"
            >
              {{ form.routeName }}
            </el-form-item></el-col
          >
        </el-form>
      </el-row>
    </div>
    <el-row>
      <el-col :span="12" :push="1">定点加油站：</el-col>
      <el-col :span="12" :pull="1">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="small "
          @click="handleAdd"
          style="float: right"
          v-hasPermi="['pfservercar:item:add']"
          >新增</el-button
        ></el-col
      >
    </el-row>
    <!--加油站数据展示 -->
    <el-table :data="cardList" border v-loading="loading">
      <el-table-column label="加油站名称" align="center" prop="name" />
      <el-table-column label="加油站位置" align="center" prop="address" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small "
            type="primary"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pfservercar:item:edit']"
            style="color: #fff"
            >编辑</el-button
          >
          <el-button
            size="small "
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pfservercar:item:delete']"
            style="color: #fff"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改油卡管理对话框 -->
    <el-dialog
      :title="title"
      v-loading="loading"
      :visible.sync="open"
      width="500px"
      append-to-body
    >
      <el-form ref="addform" :model="addform" :rules="rules" label-width="auto">
        <el-form-item label="加油站名称:" prop="name">
          <el-input
            v-model="addform.name"
            placeholder="请输入加油站名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="加油站位置:" prop="address">
          <el-input
            v-model="addform.address"
            placeholder="请输入加油站位置"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          type="primary"
          :loading="btn_loading"
          @click="submitForm('form')"
          >确 定</el-button
        >
        <!-- <el-button @click="cancel">取 消</el-button> -->
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  I04OilPlantsAddressList,
  I03OilPlantsRouteInfo,
  I04OilPlantsAddressAdd,
  I04OilPlantsAddressEditSave,
  I04OilPlantsAddressDelete,
} from "@/api/card/see";

export default {
  components: {},
  props: [],
  data() {
    return {
      btn_loading: false,
      loading: true,
      title: "",
      // 是否显示弹出层
      open: false,
      form: {
        name: "",
        routeName: "",
      },
      addform: {
        id: undefined,
        placeId: undefined,
        name: undefined,
        address: undefined,
      },
      // 加油站数据
      cardList: [],

      rules: {
        name: [
          { required: true, message: "请输入加油站名称", trigger: "blur" },
        ],
        address: [
          { required: true, message: "请输入加油站位置", trigger: "blur" },
        ],
      },
    };
  },
  computed: {},
  watch: {},
  created() {
    this.getI03OilPlantsRouteInfo();
    this.getI04OilPlantsAddressList();
  },
  mounted() {},
  methods: {
    // 加油站数据
    getI04OilPlantsAddressList() {
      this.loading = true;
      I04OilPlantsAddressList({ placeId: this.$route.params.id }).then(res => {
        this.cardList = res.rows;
        // this.addform.placeId = res.rows[0].placeId;
        // console.log(this.addform.placeId);
        this.loading = false;
      });
    },
    // 定点管理-定点详情信息查看
    getI03OilPlantsRouteInfo() {
      I03OilPlantsRouteInfo(this.$route.params.id).then((res) => {
        this.form = res.data;
        // this.addform.placeId = res.data.id;
        // console.log(this.addform.placeId);
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加";
      this.addform.placeId = this.$route.params.id;
      console.log(this.addform.placeId);
    },
    // 清空
    reset() {
      this.addform = {
        id: null,
        placeId: null,
        name: null,
        address: null,
      };
      // this.resetForm("addform");
    },
    // 修改
    handleUpdate(row) {
      this.open = true;
      this.title = "修改";
      (this.addform.name = row.name), (this.addform.address = row.address);
      this.addform.id = row.id;
      this.addform.placeId = row.placeId;
    },
    // 删除
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm("是否确认删除", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return I04OilPlantsAddressDelete(ids);
        })
        .then(() => {
          this.getI04OilPlantsAddressList();
          this.msgSuccess("删除成功");
        });
      // I04OilPlantsAddressDelete(row.id).then(res => {
      //   this.getI04OilPlantsAddressList();
      // });
    },
    //提交
    submitForm(form) {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.btn_loading = true;
          setTimeout(() => {
            this.btn_loading = false;
          }, 1000);
          if (this.addform.id != null) {
            I04OilPlantsAddressEditSave(this.addform).then((res) => {
              this.$message({
                message: "修改成功",
                type: "success",
              });
              this.open = false;
              this.getI04OilPlantsAddressList();
              this.reset();
            });
          } else {
            I04OilPlantsAddressAdd(this.addform).then((res) => {
              this.$message({
                message: "新增成功",
                type: "success",
              });
              this.reset();
              this.open = false;
              this.getI04OilPlantsAddressList();
            });
          }
        }
      });
    },
    resetForm() {
      //   this.$refs["elForm"].resetFields();
      this.$router.go(-1);
    },
  },
};
</script>

<style lang="scss" scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
}
.text {
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
  left: 10%;
  top: 50%;
  z-index: 1;
  float: left;
}
.box {
  margin: 30px 50px 10px 0;
  text-align: right;
  .btn-group {
    display: inline-block;
  }
}
</style>
