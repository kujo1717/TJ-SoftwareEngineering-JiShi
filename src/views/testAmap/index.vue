<template>
  <div>
    <el-button @click="siteDialog = !siteDialog" type="primary"
      >选择地图地址</el-button
    >
    <el-button @click="getLngLatLocation">定位</el-button>
    <br />
    <el-button
      style="margin-top: 2em"
      @click="siteDialog_2 = !siteDialog_2"
      type="primary"
      >周围的活动</el-button
    >
    <el-button @click="getLngLatLocation_2">定位_2</el-button>

    <Point
      v-if="primitiveData.length > 1"
      :dialogShow.sync="siteDialog"
      @locationSure="locationSure"
      :primitiveData_comp="primitiveData"
    ></Point>

    <MapAround
      v-if="primitiveData_2.length > 1"
      :dialogShow.sync="siteDialog_2"
      @locationSure="locationSure_2"
      :primitiveData_comp="primitiveData_2"
    ></MapAround>

    <el-button @click="TestGetImg">TestGetImg</el-button>
    <br />
    <el-image :src="img_url" fit="fill" />
  </div>
</template>

<script>
// import { AMapManager } from "vue-amap";
import Point from "./comp.vue";
import MapAround from "./maparound.vue";
import { getImg } from "@/api/file";
export default {
  data() {
    return {
      img_base: [],
      //map 1
      siteDialog: false,
      //经度纬度
      primitiveData: [],

      //map 2
      siteDialog_2: false,
      primitiveData_2: [],
    };
  },
  components: { Point, MapAround },
  methods: {
    //get one img
    TestGetImg() {
      getImg("/activity/illus/1598973028304191489/404.a57b6f31.png.png")
        .then((res) => {
          this.img_base = res.data.bytes;
          // console.log("TestGetImg:res", res);
          // this.img_url = window.URL.createObjectURL(res.data.bytes);
        })
        .catch((err) => {
          console.log("TestGetImg:err", err);
        });
    },

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

    locationSure_2(val) {
      console.log(val);
      this.primitiveData = val.value;
      console.log("primitiveData:", this.primitiveData);
    },
    getLngLatLocation_2() {
      let that = this;
      AMap.plugin("AMap.CitySearch", function () {
        var citySearch = new AMap.CitySearch();
        citySearch.getLocalCity(function (status, result) {
          if (status === "complete" && result.info === "OK") {
            // 查询成功，result即为当前所在城市信息
            console.log("通过ip获取当前城市：", result);
            that.primitiveData_2 = [
              (result.bounds.northeast.lng + result.bounds.southwest.lng) / 2,
              (result.bounds.northeast.lat + result.bounds.southwest.lat) / 2,
            ];
            console.log("primitiveData_2", that.primitiveData_2);
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
  computed: {
    img_url() {
      return "data:image/png;base64," + this.img_base;
    },
  },
  mounted() {
    setTimeout(() => {
      this.getLngLatLocation();
    }, 100);
  },
};
</script>