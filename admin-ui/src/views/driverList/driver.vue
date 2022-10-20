<template>
  <div>
    <Header :show="true" :showRight="false"></Header>

    <div class="app-container">
      <van-row>
        <!--<van-col :span="12">
          <van-field
            readonly
            clickable
            is-link
            arrow-direction="down"
            :value="form.name"
            label="姓名:"
            placeholder="点击选择姓名"
            label-width="auto"
            @click="showPicker = true"
          />
          <van-popup v-model="showPicker" position="bottom">
            <van-picker
              value-key="name"
              show-toolbar
              :columns="columns1"
              @confirm="onConfirm"
              @cancel="showPicker = false"
            />
          </van-popup>
        </van-col>-->
        <!-- 搜索 -->
        <van-col :span="12">
          <van-search
                  v-model="form.name"
                  shape="round"
                  style="padding:5px"
                  placeholder="请输入姓名"
                  @search="onSearch"
                  @cancel="onCancel"
          />
        </van-col>

        <van-col :span="12">
          <van-search
            v-model="form.contact"
            shape="round"
            style="padding:5px"
            placeholder="请输入手机号"
            @search="onSearch"
            @cancel="onCancel"
          />
        </van-col>
      </van-row>
      <div
        class="box"
        v-for="(item, index) in columns"
        :key="index"
        @click="handClick(item.id)"
        style="cursor:pointer;padding-bottom:0.3rem"
      >
        <van-row>
          <van-col :span="12">
            <van-field
              label="姓名:"
              :value="item.name"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              label="性别:"
              :value="item.sex==0?'男':'女'"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-field
              label="手机号:"
              :value="item.contact"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-field
              label="身份证:"
              :value="item.idCard"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-field
              label="入职时间:"
              :value="item.dntryTime"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-button type="primary" @click.stop="handleCliCkmaterial(item.id)" round size="small" style="width:3rem">附件</van-button>
            <!-- <button @click.stop="handleCliCkmaterial(item.id)">资料</button> -->
          </van-col>
        </van-row>
      </div>
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header'
import { archives } from '@/api/driverList/index'
import Vue from 'vue'
import { Search } from 'vant'
import { Toast } from 'vant'
Vue.use(Search)
export default {
  components: {
    Header,
  },

  data() {
    return {
      value: '',
      columns: [],
      columns1:[],
      showPicker: false,
      form: {
        id: '',
        name: '',
        contact: '',
      },
    }
  },
  mounted() {
    this.getarchives()
     archives(this.form).then((res) => {
       res.data.rows.unshift({name:'全部'})
       
        this.columns1 = res.data.rows
      })
  },
  methods: {
    // 手机号搜索
    onSearch() {
      // this.form.contact = val
      this.getarchives()
    },
    onCancel() {
      Toast('取消')
    },

    // 获取列表
    getarchives() {
      if(this.form.name == '全部'){
        this.form.name = ''
      }
      archives(this.form).then((res) => {
        this.columns = res.data.rows
       
      })
    },
    // 资料按钮
    handleCliCkmaterial(id) {
      this.$router.push('/material/'+id)
    },

    handClick(id) {
      this.$router.push('/driverDetails/' + id)
      // console.log(id)
    },
    onConfirm(value) {
      this.form.name = value.name
      this.showPicker = false
      this.getarchives()
    },
  },
}
</script>

<style lang="scss" scoped>
.text {
  margin-bottom: 0.2rem;
}
</style>
