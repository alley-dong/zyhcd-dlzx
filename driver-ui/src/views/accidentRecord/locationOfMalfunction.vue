<template>
  <div style="padding-top:50px;">
    <Header
      :show="true"
      :showRight="false"
      :buttonShow="true"
      @handleClick="confirm"
      buttonText="完成"
      >></Header
    >
    <van-form
      @on-cancel="cancel"
      v-model="showMapComponent"
      width="800"
      :closable="false"
      :mask-closable="false"
    >
      <baidu-map
        v-bind:style="mapStyle"
        class="bm-view"
        ak="你的密钥"
        :center="center"
        :zoom="zoom"
        :scroll-wheel-zoom="true"
        @moving="syncCenterAndZoom"
        @moveend="syncCenterAndZoom"
        @zoomend="syncCenterAndZoom"
        @ready="handler"
      >
        <bm-geolocation
          anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
          :showAddressBar="true"
          :autoLocation="true"
        ></bm-geolocation>
        <bm-view style="width: 100%; height:610px;"></bm-view>
        <bm-marker
          :position="{ lng: center.lng, lat: center.lat }"
          :dragging="true"
          animation="BMAP_ANIMATION_BOUNCE"
        >
        </bm-marker>

        <bm-control>
          <bm-auto-complete v-model="keyword" :sugStyle="{ zIndex: 999999 }">
            <input
              type="text"
              placeholder="请输入搜索关键字"
              class="serachinput"
            />
          </bm-auto-complete>
        </bm-control>
        <bm-local-search
          :keyword="keyword"
          :auto-viewport="true"
          style="width:0px;height:0px;overflow: hidden;"
        ></bm-local-search>
      </baidu-map>
      <!-- <div slot="footer" style="margin-top: 0px;">
        <Button @click="cancel" type="ghost" style="width: 60px;height: 36px;"
          >取消
        </Button>
        <Button
          type="primary"
          style="width: 60px;height: 36px;"
          @click="confirm"
          >确定</Button
        >
      </div> -->
    </van-form>
  </div>
</template>
<script>
import Header from '@/components/Header'
import {
  BaiduMap,
  BmControl,
  BmView,
  BmAutoComplete,
  BmLocalSearch,
  BmMarker,
} from 'vue-baidu-map'
export default {
  components: {
    BaiduMap,
    BmControl,
    BmView,
    BmAutoComplete,
    BmLocalSearch,
    BmMarker,
    Header,
  },
  data: function() {
    return {
      showMapComponent: this.value,
      keyword: '',
      map: {},
      mapStyle: {
        width: '100%',
        height: this.mapHeight + 'px',
      },
      city: '',
      center: { lng: 116.404, lat: 39.915, name: undefined },
      zoom: 15,
    }
  },
  watch: {
    value: function(currentValue) {
      this.showMapComponent = currentValue
      if (currentValue) {
        this.keyword = ''
      }
    },
  },
  props: {
    value: Boolean,
    mapHeight: {
      type: Number,
      default: 500,
    },
  },
  methods: {
    // 获取地址
    handler({ BMap, map }) {
      const geoCoder = new BMap.Geocoder()
      this.map = map //这里的map就是百度地图的实例，把他赋给一个变量保存。
      // console.log(this.map)
      geoCoder.getLocation(new BMap.Point(this.center), (result) => {
        if (result) {
          // this.keyword = result.address
          console.log(result.address)
        }
      })
    },
    /***
     * 地图点击事件。
     */
    getClickInfo(e) {
      this.center.lng = e.point.lng
      this.center.lat = e.point.lat
    },
    syncCenterAndZoom(e) {
      const { lng, lat } = e.target.getCenter()
      this.center.lng = lng
      this.center.lat = lat
    },
    /***
     * 确认
     */
    confirm: function() {
      this.showMapComponent = false
      // console.log(this.center)
      this.center.name = this.keyword
      // this.$bus.$emit('map-confirm', this.center)
      this.$store.dispatch('SetData', this.center)
      this.$router.go(-1)
    },
    /***
     * 取消
     */
    cancel: function() {
      this.showMapComponent = false
      this.$emit('cancel', this.showMapComponent)
    },
  },
}
</script>

<style scoped>
.serachinput {
  width: 374px;
  box-sizing: border-box;
  padding: 9px;
  border: 1px solid #dddee1;
  line-height: 20px;
  font-size: 16px;
  height: 38px;
  color: #333;
  position: relative;
  border-radius: 4px;
  -webkit-box-shadow: #666 0px 0px 10px;
  -moz-box-shadow: #666 0px 0px 10px;
  box-shadow: #666 0px 0px 10px;
}
</style>
