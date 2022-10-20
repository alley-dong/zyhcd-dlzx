<template>
  <div class="app-container">
    <!-- 线路管理修改 -->
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick">返回</el-button>
        <el-button type="primary" @click="editClick">提交</el-button>
        <!--size="small"  -->
      </div>
    </div>
    <div class="border">
      <div class="text">修改</div>
      <div class="xian"></div>
    </div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="140px"
    >
      <el-form-item label="线路编码:" prop="code">
        <div>
          {{ formData.id }}
        </div>
      </el-form-item>
      <el-form-item label="线路名称:" prop="name">
        <div>
          {{ formData.name }}
        </div>
      </el-form-item>
      <el-form-item label="始发地:" prop="origin">
        <div>
          {{ formData.origin }}
        </div>
      </el-form-item>
      <el-form-item label="目的地:" prop="destination">
        <div>
          {{ formData.destination }}
        </div>
      </el-form-item>
      <el-form-item label="线路里程:" prop="mileage">
        <div>
          {{ formData.mileage }}
        </div>
      </el-form-item>
      <el-form-item label="*最少在途点检次数:" prop="minInspect">
        <el-input
          v-model="formData.minInspect"
          placeholder="请输入最少在途点检次数"
          clearable
          :style="{ width: '100%' }"
        >
        </el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {
  tRouteP01RouteInfo,
  tRouteP02RouteEditSave,
} from "@/api/taskManagement/index";
export default {
  data() {
    return {
      formData: {
        name: undefined,
        origin: undefined,
        destination: undefined,
        mileage: null,
        minInspect: undefined,
        code: undefined,
      },
      rules: {
        minInspect: [],
      },
    };
  },
  mounted() {
    this.formData.id = this.$route.params.id;
    tRouteP01RouteInfo({id:this.$route.params.id}).then(res=>{
      this.formData = res.data
    })
  },
  methods: {
    backClick() {
      this.$router.go(-1);
    },
    editClick() {
      if (!this.formData.minInspect) {
        this.$message({
          message: "请填写最少在途点检次数",
          type: "warning",
        });
        return;
      }
      tRouteP02RouteEditSave(this.formData).then((res) => {
        this.backClick();
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.border {
  display: flex;
  width: 100%;
  height: 40px;
  margin-bottom: 20px;
  .text {
    width: 50px;
    text-align: right;
    line-height: 40px;
    margin-right: 30px;
  }
  .xian {
    flex: 1;
    border-bottom: 3px solid #333399;
    height: 50%;
  }
}
</style>