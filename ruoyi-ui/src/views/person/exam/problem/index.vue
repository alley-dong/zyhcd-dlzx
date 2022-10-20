<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="司机ID" prop="driverId">
        <el-input
          v-model="queryParams.driverId"
          placeholder="请输入司机ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="司机考试ID" prop="examId">
        <el-input
          v-model="queryParams.examId"
          placeholder="请输入司机考试ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="问题ID" prop="problemId">
        <el-input
          v-model="queryParams.problemId"
          placeholder="请输入问题ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="题目" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入题目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="题型 1单选题 2多选题 3判断题" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择题型 1单选题 2多选题 3判断题" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="选项A" prop="optionA">
        <el-input
          v-model="queryParams.optionA"
          placeholder="请输入选项A"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="选项B" prop="optionB">
        <el-input
          v-model="queryParams.optionB"
          placeholder="请输入选项B"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="选项C" prop="optionC">
        <el-input
          v-model="queryParams.optionC"
          placeholder="请输入选项C"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="选项D" prop="optionD">
        <el-input
          v-model="queryParams.optionD"
          placeholder="请输入选项D"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="回答答案" prop="answer">
        <el-input
          v-model="queryParams.answer"
          placeholder="请输入回答答案"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="得分分数" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入得分分数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['pfserverperson:problem:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pfserverperson:problem:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pfserverperson:problem:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pfserverperson:problem:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="problemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="司机ID" align="center" prop="driverId" />
      <el-table-column label="司机考试ID" align="center" prop="examId" />
      <el-table-column label="问题ID" align="center" prop="problemId" />
      <el-table-column label="题目" align="center" prop="name" />
      <el-table-column label="题型 1单选题 2多选题 3判断题" align="center" prop="type" />
      <el-table-column label="选项A" align="center" prop="optionA" />
      <el-table-column label="选项B" align="center" prop="optionB" />
      <el-table-column label="选项C" align="center" prop="optionC" />
      <el-table-column label="选项D" align="center" prop="optionD" />
      <el-table-column label="回答答案" align="center" prop="answer" />
      <el-table-column label="得分分数" align="center" prop="score" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pfserverperson:problem:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pfserverperson:problem:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改驾驶员-司机试卷问题对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="司机ID" prop="driverId">
          <el-input v-model="form.driverId" placeholder="请输入司机ID" />
        </el-form-item>
        <el-form-item label="司机考试ID" prop="examId">
          <el-input v-model="form.examId" placeholder="请输入司机考试ID" />
        </el-form-item>
        <el-form-item label="问题ID" prop="problemId">
          <el-input v-model="form.problemId" placeholder="请输入问题ID" />
        </el-form-item>
        <el-form-item label="题目" prop="name">
          <el-input v-model="form.name" placeholder="请输入题目" />
        </el-form-item>
        <el-form-item label="题型 1单选题 2多选题 3判断题" prop="type">
          <el-select v-model="form.type" placeholder="请选择题型 1单选题 2多选题 3判断题">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="选项A" prop="optionA">
          <el-input v-model="form.optionA" placeholder="请输入选项A" />
        </el-form-item>
        <el-form-item label="选项B" prop="optionB">
          <el-input v-model="form.optionB" placeholder="请输入选项B" />
        </el-form-item>
        <el-form-item label="选项C" prop="optionC">
          <el-input v-model="form.optionC" placeholder="请输入选项C" />
        </el-form-item>
        <el-form-item label="选项D" prop="optionD">
          <el-input v-model="form.optionD" placeholder="请输入选项D" />
        </el-form-item>
        <el-form-item label="回答答案" prop="answer">
          <el-input v-model="form.answer" placeholder="请输入回答答案" />
        </el-form-item>
        <el-form-item label="得分分数" prop="score">
          <el-input v-model="form.score" placeholder="请输入得分分数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProblem, getProblem, delProblem, addProblem, updateProblem } from "@/api/person/problem";

export default {
  name: "Problem",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 驾驶员-司机试卷问题表格数据
      problemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        driverId: null,
        examId: null,
        problemId: null,
        name: null,
        type: null,
        optionA: null,
        optionB: null,
        optionC: null,
        optionD: null,
        answer: null,
        score: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询驾驶员-司机试卷问题列表 */
    getList() {
      this.loading = true;
      listProblem(this.queryParams).then(response => {
        this.problemList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        driverId: null,
        examId: null,
        problemId: null,
        name: null,
        type: null,
        optionA: null,
        optionB: null,
        optionC: null,
        optionD: null,
        answer: null,
        score: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加驾驶员-司机试卷问题";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProblem(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改驾驶员-司机试卷问题";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProblem(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProblem(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除驾驶员-司机试卷问题编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProblem(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pfserverperson/problem/export', {
        ...this.queryParams
      }, `pfserverperson_problem.xlsx`)
    }
  }
};
</script>