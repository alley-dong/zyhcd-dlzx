<template>
  <div class="app-container">
    <el-row :gutter="15">
      <el-col :span="17">
        <el-form
          ref="elForm"
          :model="formData"
          :rules="rules"
          size="medium"
          label-width="100px"
        >
          <el-form-item label="试卷名称" prop="name">
            <el-input
              v-model="formData.name"
              placeholder="请输入试卷名称"
              show-word-limit
              clearable
              :style="{ width: '50%' }"
            ></el-input>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="7">
        <div style="width: 98%; margin: 0 20px 20px; overflow: hidden">
          <el-button
            type="primary"
            style="float: right; margin-right: 10px"
            @click="saveClick"
            :loading="loading"
        v-hasPermi="['person:dExamPaperProblem:add']"
            >提交</el-button
          >
          <!-- <router-link :to="{ path: '/addTrainingInfo', name: 'addTrainingInfo' }"> -->
          <el-button
            type="primary"
            @click="cancelClick"
            style="float: right; margin-right: 10px"
            >返回</el-button
          >
          <!-- </router-link> -->
        </div>
      </el-col>
    </el-row>

    <div style="margin: 20px 0">选择试题</div>
    <el-tree
      :data="data"
      show-checkbox
      node-key="id"
      :default-expanded-keys="[1, 2, 3]"
      @check-change="handleCheckChange"
      :props="defaultProps"
    >
      <!-- :default-checked-keys="[5]" -->
    </el-tree>
  </div>
</template>

<script>
import { paperManageAdd1,dExamProblemListS } from "@/api/driver/paperManage";

export default {
  data() {
    return {
      data: [
       
      ],
      formData: {
        name: undefined
      },
      loading: false,
      rules: {
        name: [],
      },
      //   ids:[],
      radioNum: 0, //单选
      checkNum: 0, //多选
      judgeNum: 0, //判断
      defaultProps: {
        children: "children",
        label: "label",
      },
    };
  },
  mounted () {
    this.dExamProblemDataListS()
  },
  methods: {
    handleCheckChange(data, checked, indeterminate) {
      data.state = checked;
      // if (checked) {
      //   this.ids.push(data.id);
      // }
      this.seeLength();
      console.log(data, checked, indeterminate);
    },
    dExamProblemDataListS(){
      dExamProblemListS({}).then(res=>{
        // console.log(res.rows);
        this.dataHandle(res.rows)
        this.seeLength()
        // dataHandle
      })
    },
    // 查看题的长度
    seeLength() {
      function checkAdult(val) {
        return val.state == true;
      }
        console.log(this.data);
      for (const [index, item] of this.data.entries()) {
        let len = item.children.filter(checkAdult).length;
        switch (index) {
          case 0:
            item.label = `单选题  ${len}/40`;
            this.radioNum = len;
            break;
          case 1:
            item.label = `多选题  ${len}/15`;
            this.checkNum = len;
            break;
          case 2:
            item.label = `判断题  ${len}/15`;
            this.judgeNum = len;
            break;
          default:
            break;
        }
        console.log();
      }
    },

    // 后端回来的数据处理 TODO:
    dataHandle(data) {
      let arr = [
        {
          id: 1,
          label: "单选题",
          children: [],
        },
        {
          id: 2,
          label: "多选题",
          children: [],
        },
        {
          id: 3,
          label: "判断题",
          children: [],
        },
      ];
      // let obj =
      for (const item of data) {
        let obj = {};
        switch (item.type) {
          case 1:
            obj = {};
            obj.id = item.id;
            obj.label = item.name;
            obj.state = false;
            arr[item.type - 1].children.push(obj);
            break;
          case 2:
            obj = {};
            obj.id = item.id;
            obj.label = item.name;
            obj.state = false;
            arr[item.type - 1].children.push(obj);
            break;
          case 3:
            obj = {};
            obj.id = item.id;
            obj.label = item.name;
            obj.state = false;
            arr[item.type - 1].children.push(obj);
            break;
          default:
            break;
        }
      }
      this.data = arr
    },
    saveClick() {
      if (this.radioNum!=40) {
         this.$message({
          message: '单选题必须选40道',
          type: 'warning'
        });
        return
      }
         if (this.checkNum!=15) {
         this.$message({
          message: '多选题必须选15道',
          type: 'warning'
        });
        return
      }
         if (this.judgeNum!=15) {
         this.$message({
          message: '判断题必须选15道',
          type: 'warning'
        });
        return
      }
      let ids = [];
      for (const item of this.data) {
        for (const data of item.children) {
          if (data.state) {
            ids.push(data.id);
          }
        }
      }
      //   console.log(this.radioNum);
      //   console.log(this.checkNum);
      //   console.log(this.judgeNum);
      ids = ids.join(",");
      let obj = { ids };
      obj.name = this.formData.name;
      this.loading = true;
 if (!obj.name) {
         this.$message({
          message: '请输入试卷名称',
          type: 'warning'
        });
      this.loading = false;
        return
      }
      console.log(obj);
      paperManageAdd1(obj).then((res) => {
        console.log(res);
        this.cancelClick();
      
      }).catch(this.loading = false);
    },
    cancelClick() {
      this.$router.go(-1)
      // this.$router.push({ path: "/paperManage", name: "paperManage" });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>