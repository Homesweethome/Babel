<template>
    <g>
        <node
                v-for="node in nodeElementsByFloor"
                :data="node"
                :key="node.id"
        />
        {{nodeElements}}
    </g>
</template>

<script>
    import { mapState} from 'vuex'
    import Node from './Node'
    export default {
        name: "NodeWrapper",
        components: {
            Node
        },
        computed:{
            ...mapState('editor', {
                nodeElements: 'nodeElements',
                selectFloorId: 'selectFloorId'
            }),
            nodeElementsByFloor(){
                return this.nodeElements.filter(n=>n.level==this.selectFloorId)
            }
        }
    }
</script>

<style scoped>

</style>
