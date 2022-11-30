<template>
  <div>
    <el-button @click="siteDialog = !siteDialog" type="primary"
      >选择地图地址</el-button
    >
    <el-button @click="getLngLatLocation">定位</el-button>

    <Point
      v-if="primitiveData.length > 1"
      :dialogShow.sync="siteDialog"
      @locationSure="locationSure"
      :primitiveData_comp="primitiveData"
    ></Point>
  </div>
</template>

<script>
// import { AMapManager } from "vue-amap";
import Point from "./comp.vue";
export default {
  data() {
    return {
      siteDialog: false,
      //经度纬度
      primitiveData: [],
    };
  },
  components: { Point },
  methods: {
    locationSure(val) {
      console.log(val);
      this.primitiveData = val.value;
      console.log("primitiveData:", this.primitiveData);
    },
    getLngLatLocation() {
      let that = this;
      AMap.plugin("AMap.CitySearch", function () {
        var citySearch = new AMap.CitySearch();
        citySearch.getLocalCity(function (status, result) {
          if (status === "complete" && result.info === "OK") {
            // 查询成功，result即为当前所在城市信息
            console.log("通过ip获取当前城市：", result);
            that.primitiveData = [
              (result.bounds.northeast.lng + result.bounds.southwest.lng) / 2,
              (result.bounds.northeast.lat + result.bounds.southwest.lat) / 2,
            ];
            console.log("primitiveData", that.primitiveData);
            //逆向地理编码
            AMap.plugin("AMap.Geocoder", function () {
              var geocoder = new AMap.Geocoder({
                // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
                city: result.adcode,
              });

              var lnglat = result.rectangle.split(";")[0].split(",");

              geocoder.getAddress(lnglat, function (status, data) {
                if (status === "complete" && data.info === "OK") {
                  // result为对应的地理位置详细信息
                  console.log(data);
                }
              });
            });
          }
        });
      });
    },
  },
  mounted() {
    setTimeout(() => {
      this.getLngLatLocation();
    }, 100);
  },
};
</script>