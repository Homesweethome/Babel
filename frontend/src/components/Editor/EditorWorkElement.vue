<template>
    <v-navigation-drawer>
        <v-subheader>
            Режимы работы
        </v-subheader>
        <v-list dense
                shaped
        >
            <v-list-item-group v-model="modeEditors" color="primary">
                <v-list-item
                        v-for="mode in modes"
                        @click="setMode(mode.event)"
                        :key="mode.event"
                >
                    <v-list-item-action>
                        <v-icon class="white--text">{{mode.icon}}</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title class="text-left white--text">{{mode.title}}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list-item-group>
        </v-list>
        <v-subheader>
            Элементы здания
        </v-subheader>
        <v-list shaped  :disabled="modeEditor!='draw'">
            <v-list-item-group v-model="homeElement" color="warning" class="white--text">
                <v-list-item
                        link
                        v-for="(homeElement, i) in homeElements"
                        :key="i"
                        @click="setDrawTypeElement(homeElement.value)"
                >
                    <v-list-item-action>
                        <v-icon class="white--text">{{homeElement.icon}}</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title class="text-left white--text">{{homeElement.title}}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-subheader>
                    Тарги
                </v-subheader>
                <v-divider />
                <v-list-item
                        link
                        v-for="(element, i) in nodeElements"
                        :key="i+homeElements.length"
                        @click="setDrawTypeElement(element.value)"
                >
                    <v-list-item-action>
                        <v-icon class="white--text">{{element.icon}}</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title class="text-left white--text">{{element.title}}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list-item-group>

        </v-list>

        <v-list shaped dense :disabled="modeEditor!='draw'">
            <v-list-item-group v-model="homeElement" color="error">

            </v-list-item-group>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    export default {
        name: "EditorWorkElement",
        data(){
            return{
                modeEditors: '1',
                modes: [
                    {
                        title: 'Рисование',
                        icon: 'edit',
                        event: 'draw'
                    },
                    {
                        title: 'Выбор объекта',
                        icon: 'check',
                        event: 'select'
                    }
                ],
                homeElement: '1',
                homeElements: [
                    {
                        title: 'Комната',
                        icon: 'domain',
                        value: 'room'
                    },
                    {
                        title: 'Стена',
                        icon: '',
                        value: 'wall'
                    },
                    {
                        title: 'Стеллаж',
                        icon: '',
                        value: 'rack'
                    },
                    {
                        title: 'Кафедра',
                        icon: '',
                        value: 'chair'
                    },
                    {
                        title: 'Место для чтения',
                        icon: '',
                        value: 'placeforread'
                    },
                    {
                        title: 'Диван',
                        icon: '',
                        value: 'sofa'
                    },
                    {
                        title: 'Стол с ПК',
                        icon: '',
                        value: 'pc'
                    },
                    {
                        title: 'МФУ',
                        icon: '',
                        value: 'mfu'
                    },
                ],
                nodeElement: '',
                nodeElements: [
                    {
                        title: 'Дверь',
                        icon: 'radio_button_unchecked',
                        value: 'door'
                    },
                    {
                        title: 'Лестница',
                        icon: 'tab',
                        value: 'stair'
                    },
                    {
                        title: 'Лифт',
                        icon: 'devices',
                        value: 'elevator'
                    },
                    {
                        title: 'Окно',
                        icon: 'developer_board',
                        value: 'window'
                    }
                ]
            }
        },
        computed: {
            ...mapState('editor', {
                drawTypeElement: 'drawTypeElement',
                modeEditor: 'modeEditor'
            })
        },
        methods: {
            setMode(type){
                if(type=='draw') {
                    this.setDrawMode()
                }
                else {
                    this.setSelectMode()
                }
            },
            ...mapActions('editor', {
                setDrawMode: 'set_draw_mode_editor',
                setSelectMode: 'set_select_mode_editor',
                setDrawTypeElement: 'set_draw_type_element'
            })
        }
    }
</script>

<style scoped>

</style>
