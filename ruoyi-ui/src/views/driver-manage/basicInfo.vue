<template>
  <div>
    <div style="width: 100%; margin: 20px 10px 20px 0; overflow: hidden">
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="editClick"
        v-hasPermi="hasPermi"
        >编辑</el-button
      >
        <!-- v-if="btn_show" -->
      <el-button
        @click="$router.go(-1)"
        type="primary"
        style="float: right; margin-right: 10px"
        >返回</el-button
      >
    </div>
    <el-tabs
      v-model="tabNum"
      type="border-card"
      @tab-click="tabClick"
      style="width: 98%; margin: 0 auto"
    >
      <el-tab-pane>
        <span slot="label">驾驶员基本信息</span>
        <div style="display: flex">
          <el-row :gutter="15" style="width: 70%">
            <el-form
              ref="elForm"
              :model="formData"
              :rules="rules"
              size="medium"
              label-width="121px"
            >
              <el-col :span="12">
                <el-form-item label="姓名" prop="name">
                  {{ formData.name }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="性别" prop="sex">
                  {{ formData.sex == 0 ? "男" : "女" }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="出生日期">
                  {{ formData.birthday }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="身份证号">
                  {{ formData.idCard }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="民族">
                  {{ formData.nation }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="政治面貌">
                  {{ formData.politicalAffiliation }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="婚姻状况">
                  {{ formData.maritalStatus }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="学历">
                  {{ formData.education }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="联系方式" prop="contact">
                  {{ formData.contact }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="紧急联系人电话">
                  {{ formData.urgentTel }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="驾照类型">
                  {{ formData.drivingLicenseType }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="获取时间">
                  {{ formData.drivingLicenseTime }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="驾龄">
                  {{ formData.driveAge }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="入职时间">
                  {{ formData.dntryTime }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="从业资格证号">
                  {{ formData.qualification }}
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="家庭地址">
                  {{ formData.address }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="驾驶员类型">
                  {{ formData.driverTypeId }}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="员工类型">
                  {{ formData.staffType==1?'正式工':'外包工' }}
                </el-form-item>
              </el-col>
              <!-- <el-col :span="24">
                <el-form-item size="large">
                  <el-button type="primary" @click="submitForm">提交</el-button>
                  <el-button @click="resetForm">重置</el-button>
                </el-form-item>
              </el-col> -->
            </el-form>
          </el-row>
          <div>
            <img
              v-if="formData.driverPhoto"
              :src="formData.driverPhoto"
              alt=""
              srcset=""
              style="width: 300px; height: 300px"
            />
            <img
              v-else
              src="@/assets/image/头像.png"
              alt=""
              srcset=""
              style="width: 300px; height: 300px"
            />
            <div style="text-align: center; line-height: 60px; font-size: 25px">
              照片
            </div>
          </div>
        </div>
        <div>
          <div class="wrap">
            <div class="text">教育经历</div>
            <div class="line"></div>
          </div>
          <el-form :inline="true" class="demo-form-inline" :model="listQuery">
            <el-button
              type="primary"
              style="float: right; margin: 0 20px 10px 0"
              @click="educationClick"
              size="small"
              >新增</el-button
            >
          </el-form>
          <el-table
            border
            :data="educationList"
            style="width: 98%; margin: 0 auto"
          >
            <el-table-column label="就读学校(从初中起)" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.school }}</span>
              </template>
            </el-table-column>
            <el-table-column label="专业" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.major }}</span>
              </template>
            </el-table-column>
            <el-table-column label="起止时间" align="center">
              <template slot-scope="scope">
                <span
                  >{{ scope.row.startTime }} - {{ scope.row.finshTime }}</span
                >
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              align="center"
              class-name="small-padding fixed-width"
            >
              <template slot-scope="scope">
                <el-button
                  size="small"
                  @click="editEducationClick(scope.row)"
                  plain
                  type="primary"
                  >编辑</el-button
                >
                <el-button
                  size="small"
                  @click="delEducationClick(scope.row.id)"
                  type="danger"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div>
          <div class="wrap">
            <div class="text">驾驶经历</div>
            <div class="line"></div>
          </div>
          <el-form :inline="true" class="demo-form-inline" :model="listQuery">
            <el-button
              type="primary"
              style="float: right; margin: 0 20px 10px 0"
              @click="DrivingExperienceAdd"
              size="small"
              >新增</el-button
            >
          </el-form>
          <el-table
            border
            :data="A11DriverDriverDataList"
            style="width: 98%; margin: 0 auto"
          >
            <el-table-column label="工作单位" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.workUnit }}</span>
              </template>
            </el-table-column>
            <el-table-column label="职务及岗位" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.position }}</span>
              </template>
            </el-table-column>
            <el-table-column label="入职时间" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.startTime }} </span>
              </template>
            </el-table-column>
            <el-table-column label="离职时间" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.finshTime }}</span>
              </template>
            </el-table-column>
            <el-table-column label="驾驶路线" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.route }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              align="center"
              class-name="small-padding fixed-width"
            >
              <template slot-scope="scope">
                <el-button
                  type="primary"
                  plain
                  size="small"
                  @click="A11DriverDriverEditClick(scope.row.id)"
                  >编辑</el-button
                >
                <el-button
                  type="danger"
                  size="small"
                  @click="A11DriverDriverDle(scope.row.id)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </div>

        <!-- <div class="wrap">
      <div class="text">驾驶经历</div>
      <div class="line"></div>
    </div> -->
        <div>
          <div class="wrap">
            <div class="text">交通事故经历</div>
            <div class="line"></div>
          </div>
          <el-form :inline="true" class="demo-form-inline" :model="listQuery">
            <el-button
              type="primary"
              style="float: right; margin: 0 20px 10px 0"
              @click="trafficAccidentAdd"
              size="small"
              >新增</el-button
            >
          </el-form>
          <el-table
            border
            :data="trafficAccidentData"
            style="width: 98%; margin: 0 auto"
          >
            <el-table-column label="时间" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.accidentTime }}</span>
              </template>
            </el-table-column>
            <el-table-column label="地点" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.place }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="是否造成人员受伤（详细说明）"
              align="center"
            >
              <template slot-scope="scope">
                <span>{{ scope.row.remarks }}</span>
              </template>
            </el-table-column>
            <!-- <el-table-column label="离职时间" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.startTime }} - {{ scope.row.endTime }}</span>
              </template>
            </el-table-column>
            <el-table-column label="驾驶路线" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.startTime }} - {{ scope.row.endTime }}</span>
              </template>
            </el-table-column> -->
            <el-table-column
              label="操作"
              align="center"
              class-name="small-padding fixed-width"
            >
              <template slot-scope="scope">
                <el-button
                  type="primary"
                  plain
                  @click="trafficEditClick(scope.row.id)"
                  size="small"
                  >编辑</el-button
                >
                <el-button
                  type="danger"
                  size="small"
                  @click="trafficDle(scope.row.id)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div>
          <div class="wrap">
            <div class="text">家庭状况</div>
            <div class="line"></div>
          </div>
          <el-form :inline="true" class="demo-form-inline" :model="listQuery">
            <el-button
              type="primary"
              style="float: right; margin: 0 20px 10px 0"
              @click="FamilyStatusAdd()"
              size="small"
              >新增</el-button
            >
          </el-form>
          <el-table
            border
            :data="FamilyStatusDataList"
            style="width: 98%; margin: 0 auto"
          >
            <el-table-column label="关系" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.relation }}</span>
              </template>
            </el-table-column>
            <el-table-column label="姓名" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column label="联系方式" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.tel }}</span>
              </template>
            </el-table-column>
            <el-table-column label="职务及岗位" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.position }}</span>
              </template>
            </el-table-column>
            <el-table-column label="工作单位" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.workUnit }}</span>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              align="center"
              class-name="small-padding fixed-width"
            >
              <template slot-scope="scope">
                <el-button
                  type="primary"
                  @click="A11DriverFamilyEditClick(scope.row.id)"
                  plain
                  size="small"
                  >编辑</el-button
                >
                <el-button
                  type="danger"
                  size="small"
                  @click="A11DriverFamilyDeleteDel(scope.row.id)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </div>

        <div class="wrap">
          <div class="text">附件</div>
          <div class="line"></div>
        </div>
        <!-- <div class="enclosure" v-if="formData.fileUrl">
          <img
            v-for="(item, index) in formData.fileUrl"
            :key="index"
            :src="item"
            alt=""
            :style="{ marginRight: index == 0 ? '50px' : '0' }"
          />
        </div> -->
        <el-upload
          class="upload-demo"
          disabled
          :file-list="fileList"
          :action="uploadAction"
          :on-preview="uploadPreview"
          style="margin: 15px 0 50px 37px; width: 419px"
        >
        </el-upload>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label">面试记录</span>
        <!-- 面试记录 -->
        <!-- <ol>
          <li>11111111111111111111111111</li>
        </ol> -->
        <div style="padding: 17px">
          {{ formData.interviewRecord }}
        </div>
        <div>
          <div class="wrap">
            <div class="text">面试结果</div>
            <div class="line"></div>
          </div>
          <div style="margin: 20px 0; margin-left: 20px">
            {{ formData.interviewStatus | formDataCheck }}
          </div>
          <div style="margin-left: 20px">{{ formData.interviewRemarks }}</div>
        </div>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label">笔试记录</span>
        <!-- 笔试记录 -->
        <div style="border: 1px solid #ccc; padding: 0 0 50px 10px">
          <h3>单选题（ 每题1分 共40分 ）</h3>
          <ol>
            <!-- TODO:笔试没做完 -->
            <li v-for="item in radio" :key="item.id">
              <p>
                {{ item.name }}
                <span style="float: right">（正确答案：{{item.trueAnswer}} 已选答案：{{item.answer}}）</span>
              </p>
              <div>
                <div style="display: inline-block; margin-right: 20px">
                  A.{{ item.optionA }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  B.{{ item.optionB }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  C.{{ item.optionC }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  D.{{ item.optionD }}
                </div>
              </div>
            </li>
          </ol>
          <h3>多选题（ 每题2分 共30分 ）</h3>
          <ol>
            <li v-for="item in checkBox" :key="item.id">
              <p>
                {{ item.name }}

                <span style="float: right"
                  >（正确答案：{{item.trueAnswer}} 已选答案：{{item.answer}}）</span
                >
              </p>
              <div>
                <div style="display: inline-block; margin-right: 20px">
                  A.{{ item.optionA }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  B.{{ item.optionB }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  C.{{ item.optionC }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  D.{{ item.optionD }}
                </div>
              </div>
            </li>
          </ol>
          <h3>判断题（ 每题2分 共30分 ）</h3>
          <ol>
            <li v-for="item in judgment" :key="item.id">
              <p>
                {{ item.name }}
                <span style="float: right">（正确答案：{{item.trueAnswer == 'B'?'错':'对'}} 已选答案：{{item.answer?(item.answer=='B'?'错':'对'):''}} ）</span>
              </p>
            </li>
          </ol>
          <!-- 分数统计 -->
          <!-- <table border="1" class="Score-statistics">
            <tr>
              <th>单选题（40分）</th>
              <th>多选题（30分）</th>
              <th>判断题（30分）</th>
              <th>总计</th>
            </tr>
            <tr>
              <td>30</td>
              <td>30</td>
              <td>30</td>
              <td>30</td>
            </tr>
          </table> -->
          <el-table :data="tableData" border style="width: 60%; margin: auto">
            <el-table-column prop="radio" label="单选题（40分）" width="180">
            </el-table-column>
            <el-table-column prop="choice" label="多选题（30分）" width="180">
            </el-table-column>
            <el-table-column prop="judge" label="判断题（30分）">
            </el-table-column>
            <el-table-column prop="total" label="总计"> </el-table-column>
          </el-table>
          <div class="wrap">
            <div class="text" style="font-weight: 600">笔试结果</div>
            <div class="line"></div>
          </div>
          <!-- <div style="margin-left: 50px">
            <div style="margin-right: 40px">{{
              formData.examStatus | formDataCheck
            }}</div>
            <div>{{ formData.examRemarks }}</div>
          </div> -->
            <div style="margin: 20px 0; margin-left: 20px">
            {{ formData.examStatus | formDataCheck }}
          </div>
          <div style="margin-left: 20px">{{ formData.examRemarks }}</div>
        </div>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label">实操记录</span>
        <!-- 实操记录 -->
        <el-row :gutter="0">
          <el-form
            ref="elForm"
            :model="formData"
            :rules="rules"
            size="mini"
            label-width="200px"
          >
            <el-col :span="14">
              <el-form-item label="直线行驶" prop="driveStraightDrivingStatus">
                <!-- <el-radio-group
                  v-model="formData.driveStraightDrivingStatus"
                  size="medium"
                >
                  <el-radio
                    v-for="(item, index) in field101Options"
                    :key="index"
                    :label="item.value"
                    :disabled="true"
                    >{{ item.label }}</el-radio
                  >
                </el-radio-group> -->
                {{ formData.driveStraightDrivingStatus | formDataCheck }}
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <div class="Practical-operation">
                <el-form-item label="" prop="driveStraightDrivingRemarks">
                  <!-- <el-input
                    v-model="formData.field104"
                    placeholder="请输入"
                    :style="{ width: '100%' }"
                  ></el-input> -->
                  {{ formData.driveStraightDrivingRemarks }}
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="14">
              <el-form-item label="倒车入库" prop="driveParkingStatus">
                <!-- <el-radio-group
                  v-model="formData.driveParkingStatus"
                  size="medium"
                >
                  <el-radio
                    v-for="(item, index) in field101Options"
                    :key="index"
                    :label="item.value"
                    :disabled="true"
                    >{{ item.label }}</el-radio
                  > -->
                <!-- </el-radio-group> -->
                {{ formData.driveParkingStatus | formDataCheck }}
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <div class="Practical-operation">
                <el-form-item label="" prop="driveParkingRemarks">
                  {{ formData.driveParkingRemarks }}
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="14">
              <el-form-item
                label="简单的故障判断及故障处理"
                prop="driveEmergencyStatus"
              >
                <!-- <el-radio-group
                  v-model="formData.driveEmergencyStatus"
                  size="medium"
                >
                  <el-radio
                    v-for="(item, index) in field101Options"
                    :key="index"
                    :label="item.value"
                    :disabled="true"
                    >{{ item.label }}</el-radio
                  >
                </el-radio-group> -->
                {{ formData.driveEmergencyStatus | formDataCheck }}
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <div class="Practical-operation">
                <el-form-item
                  label=""
                  prop="driveEmergencyRemarks"
                  style="margin-left: 0"
                >
                  {{ formData.driveEmergencyRemarks }}
                </el-form-item>
              </div>
            </el-col>
          </el-form>
        </el-row>
        <!-- 实操结果 -->
        <div class="wrap">
          <div class="text">实操结果</div>
          <div class="line"></div>
        </div>
        <el-row :gutter="0">
          <el-col :span="10" style="text-align: right; width: 200px">
            <!-- <el-radio-group v-model="formData.driveStatus" size="medium">
              <el-radio
                v-for="(item, index) in field101Options"
                :key="index"
                :label="item.value"
                :disabled="true"
                >{{ item.label }}</el-radio
              >
            </el-radio-group> -->
            {{ formData.driveStatus | formDataCheck }}
          </el-col>
          <el-col :span="10">
            {{ formData.driveRemarks }}
            <!-- <el-input
              v-model="formData.field104"
              placeholder="请输入"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input> -->
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
    <div v-if="this.tabNum == 0"></div>

    <div v-if="this.tabNum == 3"></div>
    <educationalDialog
      v-if="educationalDialogOpen"
      :educationalDialogData="educationalDialogData"
      @educationalDialogClick="myeducationalDialogClick"
    />
    <DrivingExperienceDialog
      v-if="DrivingExperienceOpen"
      :drivingExperienceDialogData="educationalDialogData"
      @DrivingExperienceDialogClick="myDrivingExperienceDialogClick"
    />
    <trafficAccident
      v-if="trafficAccidentOpen"
      :trafficAccidentDataList="educationalDialogData"
      @trafficAccidentClick="mytrafficAccidentClick"
    />
    <familyDialog
      v-if="familyDialogOpen"
      :familyDialogData="educationalDialogData"
      @familyDialogClick="myfamilyDialogClick"
    />
  </div>
</template>

<script>
import {
  A11Info,
  educationExperienceList,
  A11DriverEducationAdd,
  A11DriverEducationDelete,
  A11DriverEducationEdit,
  A11DriverDriverList,
  A11DriverDriverAdd,
  A11DriverEducationEditSave,
  A11DriverDriverEditSave,
  A11DriverDriverDelete,
  A11DriverDriverEdit,
  A11DriverAccidentList,
  A11DriverAccidentAdd,
  A11DriverAccidentEdit,
  A11DriverAccidentEditSave,
  A11DriverAccidentDelete,
  A11DriverFamilyList,
  A11DriverFamilyAdd,
  A11DriverFamilyDelete,
  A11DriverFamilyEditSave,
  A11DriverFamilyEdit,
  driverWrittenExamination,
} from "@/api/driver/interviewManage";
import educationalDialog from "./dialog/educationalDialog";
import DrivingExperienceDialog from "./dialog/DrivingExperienceDialog";
import trafficAccident from "./dialog/trafficAccident";
import familyDialog from "./dialog/family";
import {
  A10DriverBox, //驾驶员类型下拉选
} from "@/api/driver/driverType";
export default {
  components: {
    educationalDialog,
    DrivingExperienceDialog,
    trafficAccident,
    familyDialog,
  },
  watch: {
    // data(newValue, oldValue) {

    // }
    $route(val) {
      this.A11InfoData(); // 基本信息
    },
    tabNum(val) {
      console.log(val);
      switch (val) {
        case "0": //基本信息
          this.hasPermi = ["pfserverperson:driver:info"];
          break;
        case "1": //面试信息
          this.hasPermi = ["pfserverperson:driver:interviewReview"];
          break;
        case "2": //笔试信息
          this.hasPermi = ["pfserverperson:driver:writtenReview"];
          break;
        case "3": //实操信息
          this.hasPermi = ["pfserverperson:driver:writtenReview"];
          break;

        default:
          break;
      }
    },
  },
  filters: {
        trueAnswer: function(value) {
     switch (value) {
       case 'A':
         return '对'
         break;
        case 'B':
         return '错'
       default:
         return ''
         break;
     }
    },
    data(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
    formDataCheck(val) {
      switch (val) {
        case 1:
          return "进行中";
          break;
        case 2:
          return "未通过";
          break;
        case 3:
          return "已通过";
          break;
        case 4:
          return "储备";
          break;
        default:
          break;
      }
    },
  },
  name: "basicInfo",
  data() {
    return {
      btn_show: true,
      educationalDialogOpen: false,
      DrivingExperienceOpen: false, //驾驶经历弹窗
      trafficAccidentOpen: false, //交通事故经历弹窗
      familyDialogOpen: false, //交通事故经历弹窗
      educationalDialogData: null,
      tableId: null, //表id
      tabNum: "0", //选项卡位置
      hasPermi: ["pfserverperson:driver:info"], // 权限
      checkBox: [],
      judgment: [],
      radio: [],
      formData: {
        name: "",
        sex: "",
        birthday: "",
        idCard: "",
        nation: "",
        politicalAffiliation: "",
        maritalStatus: "",
        education: "",
        contact: "",
        urgentTel: "",
        drivingLicenseType: "",
        drivingLicenseTime: "",
        driveAge: "",
        dntryTime: "",
        address: undefined,
        driverTypeId: undefined,
        staffType: undefined,
        upload: null,
        driveStraightDrivingStatus: undefined,
        driveStraightDrivingRemarks: undefined,
        driveParkingStatus: undefined,
        driveParkingRemarks: undefined,
        driveEmergencyStatus: undefined,
        driveEmergencyRemarks: undefined,
        qualification: undefined,
        fileUrl: null,
      },
      field101Options: [
        {
          label: "进行中",
          value: 1,
        },
        {
          label: "不通过",
          value: 2,
        },
        {
          label: "通过",
          value: 3,
        },
      ],

      A11DriverDriverDataList: [], //驾驶经历table
      trafficAccidentData: [], //交通事故经历table
      FamilyStatusDataList: [], //家庭经历table
      uploadAction: "https://jsonplaceholder.typicode.com/posts/",
      fileList: [],
      rules: {
        driveStraightDrivingStatus: [],
        driveStraightDrivingRemarks: [],
        driveParkingStatus: [],
        driveParkingRemarks: [],
        driveEmergencyStatus: [],
        driveEmergencyRemarks: [],
        name: [
          {
            required: true,
            message: "请输入姓名",
            trigger: "blur",
          },
        ],
        sex: [
          {
            required: true,
            message: "请输入性别",
            trigger: "blur",
          },
        ],
        birthday: [
          {
            required: true,
            message: "请输入出生日期",
            trigger: "blur",
          },
        ],
        idCard: [
          {
            required: true,
            message: "请输入身份证号",
            trigger: "blur",
          },
        ],
        nation: [
          {
            required: true,
            message: "请输入民族",
            trigger: "blur",
          },
        ],
        politicalAffiliation: [
          {
            required: true,
            message: "请输入政治面貌",
            trigger: "blur",
          },
        ],
        maritalStatus: [
          {
            required: true,
            message: "请输入婚姻状况",
            trigger: "blur",
          },
        ],
        education: [
          {
            required: true,
            message: "请输入学历",
            trigger: "blur",
          },
        ],
        contact: [
          {
            required: true,
            message: "请输入联系方式",
            trigger: "blur",
          },
        ],
        urgentTel: [
          {
            required: true,
            message: "请输入紧急联系人电话",
            trigger: "blur",
          },
        ],
        drivingLicenseType: [
          {
            required: true,
            message: "请输入驾照类型",
            trigger: "blur",
          },
        ],
        drivingLicenseTime: [
          {
            required: true,
            message: "请输入获取时间",
            trigger: "blur",
          },
        ],
        driveAge: [
          {
            required: true,
            message: "请输入驾龄",
            trigger: "blur",
          },
        ],
        dntryTime: [
          {
            required: true,
            message: "请输入入职时间",
            trigger: "blur",
          },
        ],
        address: [
          {
            required: true,
            message: "请输入家庭地址",
            trigger: "blur",
          },
        ],
        driverTypeId: [
          {
            required: true,
            message: "请输入驾驶员类型",
            trigger: "blur",
          },
        ],
        staffType: [
          {
            required: true,
            message: "请输入员工类型",
            trigger: "blur",
          },
        ],
      },
      tableData1: [],
      tableData: [],
      educationList: [],
      listQuery: {},
    };
  },
  watch: {
    tabNum(newValue, oldValue) {
      console.log(newValue);
      switch (newValue) {
        case '0':
            this.btn_show = true;
          break;

        default:
          if (this.$route.query.status == 2 || this.$route.query.status==4) {
            this.btn_show = false;
          } else {
            this.btn_show = true;
          }
          break;
      }
    },
  },
  mounted() {
    if (this.$route.query.num) {
      this.tabNum = this.$route.query.num;
      this.init();
    }
    // 获取id
    this.init();
  },

  methods: {
    init() {
      this.A11InfoData(); // 基本信息
      this.educationExperienceListData(); //教育经历列表
      this.A11DriverDriverListData(); //驾驶经历列表
      this.A11DriverAccidentListData(); //交通事故经历列表
      this.A11DriverFamilyListData(); //家庭状况列表
      this.driverWrittenExaminationData(); //笔试详情
    },
    //编辑跳页面
    editClick() {
      switch (this.tabNum) {
        // 基本信息
        case "0":
          this.$router.push("/basicInfoEdit/" + this.$route.params.id);
          break;
        // 面试
        case "1":
          this.$router.push("/basicInfoInterview/" + this.$route.params.id);
          break;
        // 笔试
        case "2":
          this.$router.push("/basicInfoWrite/" + this.$route.params.id);
          break;
        // 实操
        case "3":
          this.$router.push("/basicInfoSem/" + this.$route.params.id);
          break;
        default:
          break;
      }
    },
    // 笔试详情
    driverWrittenExaminationData() {
      driverWrittenExamination(this.$route.params.id).then((res) => {
        if (res.msg == "该驾驶员无笔试记录") {
          return;
        }
        this.checkBox = res.data.checkBox;
        this.judgment = res.data.judgment;
        this.radio = res.data.radio;
        let obj = {
        radio: res.data.checkScore,
          choice: res.data.judgmentScore,
          judge: res.data.radioScore,
          total: res.data.radioScore + res.data.checkScore + res.data.judgmentScore||'',
        };
        if (!obj.radio) {
        this.tableData = [];
          
        }else{
        this.tableData = [obj];
        }
        // debugger
      });
    },
    // 编辑
    editEducationClick(val) {
      A11DriverEducationEdit(val.id).then((res) => {
        this.educationalDialogData = res.data;
        this.educationalDialogOpen = true;
      });
    },
    // 教育经历删除TODO:
    delEducationClick(id) {
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          A11DriverEducationDelete(id).then((response) => {
            that.educationExperienceListData();
            that.$message({
              message: "删除成功",
              type: "success",
            });
          });
        })
        .catch(function () {});
      // A11DriverEducationDelete(id).then((res) => {
      //   this.educationExperienceListData();
      // });
    },
    //   教育经历弹窗TODO:
    myeducationalDialogClick(val, data) {
      if (!val) {
        this.educationalDialogOpen = false;
        this.tableId = null;
        this.educationalDialogData = null;

        return;
      }
      //TODO:编辑和新增记得测试
      val.driverId = this.$route.params.id;
      // val.finshTime = finshTime
      if (data) {
        A11DriverEducationEditSave(val).then((res) => {
          this.educationExperienceListData();
        });
      } else {
        //   TODO:修改
        A11DriverEducationAdd(val).then((res) => {
          this.educationExperienceListData();
        });
      }
      this.educationalDialogData = null;
      this.tableId = null;

      this.educationalDialogOpen = false;
    },
    // 教育经历列表TODO:
    educationExperienceListData() {
      educationExperienceList(this.$route.params.id).then((res) => {
        this.educationList = res.data;
      });
    },
    // 教育经历新增TODO:
    educationClick(val) {
      // this.tableId = val;
      this.educationalDialogOpen = true;
    },
    // 驾驶经历列表TODO:
    A11DriverDriverListData() {
      A11DriverDriverList(this.$route.params.id).then((res) => {
        this.A11DriverDriverDataList = res.data;
      });
    },

    //   驾驶经历添加TODO:
    DrivingExperienceAdd() {
      this.DrivingExperienceOpen = true;
    },
    // 驾驶经历弹窗TODO:
    myDrivingExperienceDialogClick(val, data) {
      if (!val) {
        this.DrivingExperienceOpen = false;
        this.tableId = null;
        this.educationalDialogData = null;

        return;
      }
      //TODO:编辑和新增记得测试
      val.driverId = this.$route.params.id;
      if (data) {
        A11DriverDriverAdd(val).then((res) => {
          this.A11DriverDriverListData();
        });
      } else {
        //   TODO:修改
        A11DriverDriverEditSave(val).then((res) => {
          this.A11DriverDriverListData();
        });
      }
      this.DrivingExperienceOpen = false;
      this.tableId = null;
      this.educationalDialogData = null;

      this.educationExperienceListData();
    },
    // 驾驶经历删除TODO:
    A11DriverDriverDle(id) {
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          A11DriverDriverDelete(id).then((response) => {
            that.A11DriverDriverListData();
            that.$message({
              message: "删除成功",
              type: "success",
            });
          });
        })
        .catch(function () {});
      //TODO:
      // A11DriverDriverDelete(id).then((res) => {
      //   this.A11DriverDriverListData();
      // });
    },
    // 驾驶经历编辑TODO:
    A11DriverDriverEditClick(id) {
      A11DriverDriverEdit(id).then((res) => {
        this.educationalDialogData = res.data;
        //TODO:拿到数据传到弹窗中
        this.DrivingExperienceOpen = true;
      });
    },
    // 交通事故经历删除TODO:
    trafficDle(id) {
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          A11DriverAccidentDelete(id).then((response) => {
            that.A11DriverAccidentListData();
            that.$message({
              message: "删除成功",
              type: "success",
            });
          });
        })
        .catch(function () {});
      //TODO:
      // A11DriverAccidentDelete(id).then((res) => {
      //   this.A11DriverAccidentListData();
      // });
    },
    // 交通事故经历编辑TODO:
    trafficEditClick(id) {
      A11DriverAccidentEdit(id).then((res) => {
        this.educationalDialogData = res.data;
        //TODO:拿到数据传到弹窗中
        this.trafficAccidentOpen = true;
      });
    },
    //交通事故经历新增
    trafficAccidentAdd() {
      this.trafficAccidentOpen = true;
    },
    //交通事故经历列表TODO:
    A11DriverAccidentListData() {
      A11DriverAccidentList(this.$route.params.id).then((res) => {
        this.trafficAccidentData = res.data;
      });
    },
    // 交通事故经历弹窗TODO:
    mytrafficAccidentClick(val, data) {
      if (!val) {
        this.trafficAccidentOpen = false;
        this.tableId = null;
        this.educationalDialogData = null;

        return;
      }
      //TODO:编辑和新增记得测试
      val.driverId = this.$route.params.id;
      if (!data) {
        A11DriverAccidentAdd(val).then((res) => {
          this.A11DriverAccidentListData();
        });
      } else {
        //   TODO:修改
        A11DriverAccidentEditSave(val).then((res) => {
          this.A11DriverAccidentListData();
        });
      }
      this.educationalDialogData = null;
      this.tableId = null;
      this.trafficAccidentOpen = false;

      this.educationExperienceListData();
    },
    // 家庭状况删除TODO:
    A11DriverFamilyDeleteDel(id) {
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          A11DriverFamilyDelete(id).then((response) => {
            that.A11DriverFamilyListData();
            that.$message({
              message: "删除成功",
              type: "success",
            });
          });
        })
        .catch(function () {});
      //TODO:
      // A11DriverFamilyDelete(id).then((res) => {
      //   this.A11DriverFamilyListData();
      // });
    },
    // 家庭状况编辑TODO:
    A11DriverFamilyEditClick(id) {
      A11DriverFamilyEdit(id).then((res) => {
        this.educationalDialogData = res.data;
        //TODO:拿到数据传到弹窗中
        this.familyDialogOpen = true;
      });
    },
    // 家庭状况添加
    FamilyStatusAdd(id) {
      //   this.tableId = val.id;
      this.familyDialogOpen = true;
    },
    //家庭状况列表TODO:
    A11DriverFamilyListData() {
      A11DriverFamilyList(this.$route.params.id).then((res) => {
        this.FamilyStatusDataList = res.data;
      });
    },
    // 家庭状况弹窗TODO:
    myfamilyDialogClick(val, data) {
      if (!val) {
        this.familyDialogOpen = false;
        this.tableId = null;
        this.educationalDialogData = null;

        return;
      }
      //TODO:编辑和新增记得测试
      val.driverId = this.$route.params.id;
      if (data) {
        A11DriverFamilyAdd(val).then((res) => {
          this.A11DriverFamilyListData();
        });
      } else {
        //   TODO:修改
        A11DriverFamilyEditSave(val).then((res) => {
          this.A11DriverFamilyListData();
        });
      }
      this.educationalDialogData = null;
      this.tableId = null;
      this.familyDialogOpen = false;

      this.educationExperienceListData();
    },
    //   选项卡选择的时候
    tabClick(tab) {
      this.tabNum = tab.paneName;
    },
    uploadBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2;
      if (!isRightSize) {
        this.$message.error("文件大小超过 2MB");
      }
      return isRightSize;
    },
    // 点击文件时
    uploadPreview(file) {
      if (file.name.indexOf(".") == -1) {
        return;
      }
      let arr = [".jpg", ".PNG"];
      // debugger;
      for (const item of arr) {
        if (file.name.indexOf(item) !== -1) {
          window.open(file.src, "_blank").location;
          return;
        }
      }

      // location.href = file.src

      let obj = {
        originalName: file.name,
        filename: file.name,
      };
      window.location.href =
        process.env.VUE_APP_BASE_API +
        `/pfserver-person/personFile/minioChooseBucketDownload?originalName=${obj.originalName}&filename=${obj.originalName}`;
      // window.open(
      //   `http://localhost/dev-api/pfserver-person/personFile/minioChooseBucketDownload?originalName=${obj.originalName}&filename=${obj.originalName}`
      // );
    },
    // 提交
    submitForm() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        // TODO 提交表单
      });
    },
    resetForm() {
      this.$refs["elForm"].resetFields();
    },

    // // 基本信息
    A11InfoData() {
      if (!this.$route.params.id) {
        return;
      }
      A11Info(this.$route.params.id).then((res) => {
        A10DriverBox({ type: null }).then((_) => {
          for (const item of _.data) {
            if (item.id == res.data.driverTypeId) {
              res.data.driverTypeId = item.name;
            }
          }
        });
        // TODO:
        // res.data.fileUrl = [
        //   "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1384471162,2839216573&fm=26&gp=0.jpg",
        //   "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1384471162,2839216573&fm=26&gp=0.jpg",
        // ];
        this.formData = res.data;
        let arr = [];
        for (const item of res.data.fileUrl) {
          let obj = {};
          let index = item.lastIndexOf(".");
          // obj.src = item.substring(0, index);
          obj.src = item;
          obj.name = item.split("pfserverperson/")[1];
          arr.push(obj);
        }
        this.fileList = arr;
      });
    },
  },
};
</script>

<style scoped>
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
.Score-statistics {
  margin: auto;
}
.Score-statistics th {
  width: 170px;
  text-align: center;
}
.Score-statistics td {
  text-align: center;
}
</style>
<style lang="scss">
.Practical-operation {
  .el-form-item__content {
    margin: 0 !important;
  }
}
.enclosure {
  display: flex;
  margin: 20px 60px;
  img {
    flex: 1;
    height: 300px;
  }
}
</style>
