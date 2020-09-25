<template>
    <v-bottom-navigation
            :value="activeFloorId"
            color="purple lighten-1"
            absolute
    >
        <v-btn
                @click="setActiveFloor(floor.id)"
                v-for="floor in floors"
                :key="floor.id">
            <span>этаж</span>
            <span>{{floor.title}}</span>
        </v-btn>
        <v-btn
            @click="addFloor({title: '2', value: 0})"
        > +
        </v-btn>
    </v-bottom-navigation>
</template>

<script>
    import {mapState, mapActions} from 'vuex'
    export default {
        name: "EditorFloors",
        data(){
            return{
                activeBtn: 1,
            }
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
                getFloors: 'get_all_floors'
            })
        },
        mounted() {
            this.getFloors()
        }
    }
</script>

<style scoped>

</style>
