<template>
  <div>
    <div class="route-div">
      <input id="pac-input" class="route-input" type="text" placeholder="루트를 추가해주세요." v-show="flag === 0 && routeNames.length < 10">
      <el-button type="primary" @click="addMarkers" :disabled="routeNames.length === 0">
        <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#fff" icon-name="maplocation">
          <map-location/>
        </icon-base>
      </el-button>
      <el-button type="primary" @click="initMap" :disabled="flag === 0">
        <icon-base viewBox="0 0 1024 1024" width="24" height="24" iconColor="#fff" icon-name="plusicon">
          <plus-icon/>
        </icon-base>  
      </el-button>
    </div>
    <div id="map" style="height: 480px; position: relative; overflow: hidden;"></div>
    <div class="route-tag-group">
      <el-tag v-for="(route, idx) in routeNames" :key="idx" class="route-tag"
      closable :disable-route="false" type='' @close="removeRoute(idx)">
        {{ route }}
      </el-tag>
    </div>
  </div>
</template>

<script>
/* eslint-disable no-undef */
import { mapActions, mapGetters } from 'vuex'
import MapLocation from '@/components/icon/MapLocation.vue'
import PlusIcon from '@/components/icon/PlusIcon.vue'

export default {
  name: 'RouteForm',
  components: {
    MapLocation,
    PlusIcon
  },
  props: {
    action: String
  },
  data() {
    return {
      flag: 0
    }
  },
  computed: {
    ...mapGetters(['routeGeocodes', 'routeNames', 'diaryTemp']),
  },
  methods: {
    ...mapActions(['addGeocode', 'addRoute', 'deleteRoute']),
    initMap() {
      this.flag = 0
      const map = new google.maps.Map(document.getElementById("map"), {
          center: { lat: 37.5642135 ,lng: 127.0016985 },
          zoom: 13,
      });
      const input = document.getElementById("pac-input");
      const autocomplete = new google.maps.places.Autocomplete(input, {
          fields: ["place_id", "geometry", "name", "formatted_address"],
      });
      autocomplete.bindTo("bounds", map);

      const geocoder = new google.maps.Geocoder();
      const marker = new google.maps.Marker({ map: map });
      autocomplete.addListener("place_changed", () => {
          const place = autocomplete.getPlace();
          if (!place.place_id) {
              return;
          }
          geocoder
              .geocode({ placeId: place.place_id })
              .then(({ results }) => {
              map.setZoom(15);
              map.setCenter(results[0].geometry.location)

              marker.setPlace({
                  placeId: place.place_id,
                  location: results[0].geometry.location,
              })

              let latLng = { lat: place.geometry.location.lat(), lng: place.geometry.location.lng() }
              let route = place.name
              this.addGeocode(latLng)
              this.addRoute(route)

              marker.setVisible(true)
          })
              .catch((e) => window.alert("Geocoder failed due to: " + e))
      input.value = ''
      });
    },

    removeRoute(index) {
      this.deleteRoute(index)
      this.addMarkers()
    },

    addMarkers() {
      this.flag = 1
      const map = new google.maps.Map(document.getElementById("map"), {
          center: this.routeGeocodes[this.routeGeocodes.length - 1],
          zoom: 13,
      });
      // console.log(this.routeGeocodes)
      this.routeGeocodes.forEach((each) => {
        // console.log(each)
        let labelNum = (this.routeGeocodes.indexOf(each)+1).toString()
        new google.maps.Marker({
            position: each,
            label: labelNum,
            map: map,
        });
      })
      const routePath = new google.maps.Polyline({
        path: this.routeGeocodes,
        geodesic: true,
        strokeColor: '#FF0000',
        strokeOpacity: 1.0,
        strokeWeight: 2
      })
      routePath.setMap(map)
    },

    // uploadRoute() {
    //   if (this.action === 'update') {
    //     this.routeGeocodes = this.diaryTemp.geocodes
    //     this.routeNames = this.diaryTemp.routes
    //   } else {
    //     this.routeGeocodes = []
    //     this.routeNames = []
    //   }
    // }
  },
  mounted() {
    this.initMap()
    // this.uploadRoute()
  },
}
</script>

<style scoped>
.route-div {
  text-align: left;
  margin-bottom: 10px;
}
#pac-input {
  background-color: #fff;
  font-size: 14px;
  font-weight: 300;
  padding: 0 11px 0 13px;
  text-overflow: ellipsis;
  width: 400px;
  height: 30px;
  box-shadow: none;
  border: solid 1px var(--el-border-color);
  border-radius: 5px;
  margin-right: 10px;
}
.route-tag-group {
  margin-top: 10px;
  text-align: left;
}
.route-tag {
  margin-left: 10px;
}
</style>