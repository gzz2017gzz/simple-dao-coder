/*${cnName}管理,  作者:${author},  日期:${time}*/
<template>
  <div>
    <el-col :span="8" style="border: 1px solid #ebeef5">
      <el-row>
        <el-button type="text" size="mini" @click="queryList({ id: 0, children: 'A' })">根结点列表</el-button>
        <el-button type="text" size="mini" @click="doAdd(0)">添加根结点</el-button>
      </el-row>
      <el-row>
        <el-tree default-expand-all :data="treeList" :props="{ children: 'children', label: 'name' }" style="height:calc(100vh - 140px);overflow-y: auto;">
          <span class="tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
              <el-button type="text" size="mini" @click.stop="queryList(data)" :disabled="data.leaf == 1">结点列表</el-button>
              <el-button type="text" size="mini" @click.stop="doAdd(data.${idName})">添加下级</el-button>
              <el-button type="text" size="mini" @click.stop="doEdit(data)">编辑</el-button>
              <el-button type="text" size="mini" @click.stop="doDelete(data)" :disabled="data.leaf == 0">删除</el-button>
            </span>
          </span>
        </el-tree>
      </el-row>
    </el-col>
    <el-col :span="16" style="padding-left: 20px">
      <v-table :data="dataList">
        <#list fields as field>
          <#if (field.fillType != 0)>
        <el-table-column prop="${field.lower}" label="${field.comment}"<#if (field.type == "LocalDateTime")> :formatter="dateFormat"</#if>></el-table-column>
          </#if>
        </#list>
      </v-table>
    </el-col>
    <${ClassName}Dialog ref="dialog" :queryTree="queryTree"></${ClassName}Dialog>
  </div>
</template>
<script>
import ${ClassName}Dialog from "./${ClassName}Dialog";
import { treeMix } from "@/common/tree";
export default {
  mixins: [treeMix],
  components: { ${ClassName}Dialog },
  methods: {
    /**${cnName}-树*/
    queryTree() {
      this.loading = true;
      this.rq.post("${serverName}/${classname}/tree", { dr: 0 }).then((res) => {
        if (res.code == 200)
          this.treeList = res.data;
        else
          this.$message.error(res.msg);
      });
      this.loading = false;
    },
    /**${cnName}-列表*/
    queryList(data) {
      this.rq.post("${serverName}/${classname}/list", { parentId: data.${idName} }).then(res => {
        if (res.code == 200)
          this.dataList = res.data;
        else
          this.$message.error(res.msg);
      });
      this.loading = false;
    },
    /**${cnName}-删除*/
    doDelete(row) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("${serverName}/${classname}/delete", row).then(res => {
          if (res.code == 200) {
            this.$message.success("删除成功");
            this.queryTree();
          } else this.$message.error(res.msg);
        });
      }).catch(() => { });
    },
  },
};
</script>
<style scoped lang="scss"></style>