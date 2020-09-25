<template>
    <v-container class="white--text">
        {{selectedNode}}
        <v-row>
            <v-col cols="12">
                <v-subheader class="subtitle-1">
                    Информация о объекте
                </v-subheader>
            </v-col>
            <v-col cols="12">
                <v-text-field
                        label="Название"/>
            </v-col>
            <v-col
                    cols="12">
                <v-textarea
                        rows="5"
                        label="Описание"/>
            </v-col>
            <v-col cols="12">
                <v-text-field
                        label="ИД в RUSMARC"/>
            </v-col>
            <v-subheader>
                Объекты принадлежащие по RUSMARC
            </v-subheader>
            <v-col cols="12">
                <v-list>
                    <v-list-item>
                        Объект 1
                    </v-list-item>
                    <v-list-item>
                        Объект 2
                    </v-list-item>
                    <v-list-item>
                        Объект 3
                    </v-list-item>
                </v-list>
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-btn @click="deleteElement" class="error" block>
                    Удалить
                </v-btn>
            </v-col>
            <v-col>
                <v-btn class="primary">
                    Сохранить
                </v-btn>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import {mapState, mapActions} from 'vuex'
    export default {
        name: "EditorPropertiesElement",
        computed:{
            ...mapState('editor', {
                selectedNode: 'selectedNode',
                selectedHomeElement: 'selectedHomeElement',
                selectHomeElement: 'selectHomeElement',
                selectNode: 'selectNode',
                nodeElements: 'nodeElements',
                homeElements: 'homeElements',
            })
        },
        methods:{
            ...mapActions('editor', {
                deleteHomeElement: 'delete_home_element',
                deleteNode: 'delete_node',
                getAllNodeElements: 'get_all_node_elements'
            }),
            deleteElement(){
                if (this.selectHomeElement){
                    this.deleteHomeElement(this.selectedHomeElement)
                } else if (this.selectNode){
                    this.deleteNode(this.selectedNode)
                }
            }
        }
    }
</script>

<style scoped>

</style>
