<template>
<!--    <v-bottom-navigation-->
<!--            :value="activeFloorId"-->
<!--            color="purple lighten-1"-->
<!--            absolute-->
<!--    >-->
<!--        <v-btn-->
<!--                @click="setActiveFloor(floor.id)"-->
<!--                v-for="floor in floors"-->
<!--                :key="floor.id">-->
<!--            <span>этаж</span>-->
<!--            <span>{{floor.title}}</span>-->
<!--            <v-badge-->
<!--                    @click="deleteFloor(floor.id)"-->
<!--                    style="margin: 10px -15px 0 0"-->
<!--                    color="red accent-4"-->
<!--                    icon="mdi-close"-->
<!--            >-->
<!--            </v-badge>-->
<!--        </v-btn>-->
<!--        <v-btn-->
<!--            @click="addFloor({title: '2', value: 0})"-->
<!--        > + этаж-->
<!--        </v-btn>-->
<!--        <v-btn>-->
<!--            <v-file-input-->
<!--                    accept="image/png, image/jpeg, image/bmp"-->
<!--                    placeholder="План этажа"-->
<!--                    prepend-icon="mdi-camera"-->
<!--                    multiple-->
<!--                    type="file"-->
<!--                    id="filesEducation"-->
<!--                    ref="filesEducation"-->
<!--                    @change="handleFilesEducationUpload"-->
<!--            ></v-file-input>-->
<!--        </v-btn>-->
<!--        <v-btn-->
<!--                @click="deleteFloor(this.selectFloorId)"-->
<!--        > Удалить выбранный этаж-->
<!--        </v-btn>-->
<!--    </v-bottom-navigation>-->
    <v-btn-toggle v-model="activeBtn">
        <v-btn
                @click="setActiveFloor(floor.id)"
                v-for="floor in floors"
                :key="floor.id">
            <span>{{floor.title}}</span>
            <span>этаж</span>
        </v-btn>
        <v-btn  v-show="showBtnEdit"
                @click="addFloor({title: '3', value: 0})"
        > + этаж
        </v-btn>
        <v-btn v-show="showBtnEdit">
            <v-file-input
                    accept="image/png, image/jpeg, image/bmp"
                    placeholder="План этажа"
                    prepend-icon="mdi-camera"
                    multiple
                    type="file"
                    id="filesEducation"
                    ref="filesEducation"
                    @change="handleFilesEducationUpload"
            ></v-file-input>
        </v-btn>
        <v-btn  v-show="showBtnEdit"
                @click="deleteFloor(activeFloorId)"
        > Удалить выбранный этаж
        </v-btn>
    </v-btn-toggle>
</template>

<script>
    import {mapState, mapActions} from 'vuex'
    export default {
        name: "EditorFloors",
        data(){
            return{
                filesEdu: '',
                activeBtn: 1,
            }
        },
        props:{
          showBtnEdit: false
        },
        computed:{
            ...mapState('editor', {
                floors: 'floors',
                activeFloorId: 'activeFloorId'
            })
        },
        methods:{
            ...mapActions('editor', {
                setActiveFloor: 'set_active_floor',
                addFloor: 'add_floor',
                getFloors: 'get_all_floors',
                addImage: 'add_image_for_floor',
                deleteFloor: 'delete_floor'
            }),
            handleFilesEducationUpload(files) {
                let formData = new FormData()
                console.log(files)
                for (let i = 0; i < files.length; i++) {
                    let file = files[i]
                    formData.append('files', file)
                }
                this.filesEdu = formData
                this.addImage(formData)
            },
        },
        mounted() {
            this.getFloors()
        }
    }
</script>

<style scoped>

</style>
