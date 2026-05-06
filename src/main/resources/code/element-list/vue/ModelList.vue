/*${cnName}不分页列表,作者:${author},日期:${time}*/
<template>
  <el-dialog :title="title" :visible.sync="show" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :inline="true" label-suffix=":" label-width="120px" @keyup.enter.native="loadData">
      <el-row :gutter="24">
<#list fields as field>
  <#if (field_index < 5  && field.fillType != 0)>
        <el-col :xl="5" :lg="6" :md="7" :sm="24">
          <el-form-item label="${field.comment}"<#if columns == true> v-show="condition.${field.lower}.show"</#if>>
        <#if field.fillType == 3>
            <v-select placeholder="请选${field.comment}" v-model="form.${field.lower}" dictKey="${field.dictKey}" :addBlank="true"></v-select>
        <#elseif field.fillType == 4>
            <el-date-picker size="mini" placeholder="请选${field.comment}" v-model="form.${field.lower}" type="datetime" value-format="yyyy-MM-ddTHH:mm:ss"></el-date-picker>
        <#else>           
            <el-input size="mini" placeholder="请输入${field.comment}" v-model="form.${field.lower}"></el-input>
        </#if> 
          </el-form-item>
        </el-col>
  </#if>
</#list>
        <el-col :xl="5" :lg="6" :md="7" :sm="24" style="padding: 7px 0 0 15px">
          <el-button size="mini" @click="loadDataInit" type="primary" plain>查询</el-button>
          <el-button size="mini" @click="doReset" type="primary" plain>重置</el-button>
          <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
          <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0" type="danger" style="margin-right: 10px;">删除</el-button>
  <#if excel == 1>
          <el-button size="mini" @click="doExport" style="margin:0 10px;" type="primary">导出</el-button>
          <v-upload :doImport="doImport" label="导入"></v-upload>
  </#if>
  <#if columns == true>
          <v-columns :condition="condition" :column="column" component="${ClassName}Page"></v-columns>
  </#if>
          <el-button size="mini" @click="() => allShow = !allShow" type="text">{{ allShow ? "折叠" : "展开" }}</el-button>
        </el-col>
      </el-row>
      <el-row v-if="allShow">
<#list fields as field>
  <#if (field_index >= 5  && field.fillType != 0)>
        <el-col :xl="5" :lg="6" :md="7" :sm="24">
          <el-form-item label="${field.comment}"<#if columns == true> v-show="condition.${field.lower}.show"</#if>>
        <#if field.fillType == 3>
            <v-select placeholder="请选${field.comment}" v-model="form.${field.lower}" dictKey="${field.dictKey}" :addBlank="true"></v-select>
        <#elseif field.fillType == 4>
            <el-date-picker size="mini" placeholder="请选${field.comment}" v-model="form.${field.lower}" type="datetime"></el-date-picker>
        <#else>           
            <el-input size="mini" placeholder="请输入${field.comment}" v-model="form.${field.lower}"></el-input>
        </#if> 
          </el-form-item>
        </el-col>
  </#if>
</#list>
      </el-row>
    </el-form>
    <v-table :data="dataList" @selection-change="(rows) => selectChange(rows, '${idName}')" :loading="loading">
      <el-table-column type="selection" width="40"<#if columns == true> v-if="column.choice.show"</#if>></el-table-column>
      <el-table-column type="expand"<#if columns == true> v-if="column.detail.show" width="35"</#if>>
        <template slot-scope="props">
<#list fields as field>
  <#if (field.fillType != 0)>
          <label class="label">${field.comment}</label>{{ props.row.${field.lower} }}
          <hr class="hr">
  </#if>
</#list>
        </template>
      </el-table-column>
<#list fields as field>
  <#if (field.fillType != 0 && field.showColumn==1)>
  <el-table-column prop="${field.lower}" label="${field.comment}" <#if columns == true> v-if="column.${field.lower}.show"</#if><#if (field.type == "LocalDateTime")> :formatter="dateFormat"</#if><#if field.fillType == 3> :formatter=" (row,col) => dictFormat(row,col,'${field.dictKey}')"</#if>></el-table-column>
  </#if>
</#list>
      <el-table-column label="操作" width="300" align="center" <#if columns == true> v-if="column.operate.show"</#if>>
        <template slot-scope="props">
          <el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button><el-divider direction="vertical"></el-divider>
          <el-button type="text" size="mini" @click="doDelete([props.row.${idName}])" style="color: #F56C6C;">删除</el-button><el-divider direction="vertical"></el-divider>
        </template>
      </el-table-column>
    </v-table>
    <${ClassName}Dialog ref="dialog" :loadData="loadData"></${ClassName}Dialog>
  </el-dialog>
</template>
<script>
import ${ClassName}Dialog from './${ClassName}Dialog';
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  components: { ${ClassName}Dialog },
  data() {
    return {
	  title: null, show: null,
      <#if columns == true>
      column: {
        choice: { show: true, text: "选择列" },
        detail: { show: true, text: "明细列" },
        <#list fields as field>
        <#if (field.fillType != 0)>
        ${field.lower}: { show: true, text: "${field.comment}" },
        </#if>		    
        </#list>
        operate: { show: true, text: "操作列" },
      },
      condition: {
        <#list fields as field>
        <#if (field.fillType != 0)> 
        ${field.lower}: { show: true, text: "${field.comment}" },
        </#if>
        </#list>
      },
      </#if>
    };
  },
  //computed: {}, mounted(){},
  created() { this.loadData(); },
  methods: {
  <#if excel == 1>
    /**${cnName}-导出*/
    doExport() {
      const param = { ...this.form };
      this.rq.post("${serverName}/${className}/export", param, { responseType: "blob" }).then((res) => {
        this.downloadFile(res, "${cnName}.xlsx");
      });
    },
    /**${cnName}-导入*/
    doImport(obj) {
      this.rq.post("${serverName}/${className}/import", obj.file).then(res => {
        if (res.code == 200) this.loadData(); else this.$message.error(res.msg);
      });
    },
</#if>
    /**${cnName}-查询参数*/
    initForm() {
      return {
<#list fields as field>
  <#if (field.fillType != 0)> 
        ${field.lower}: null,/*${field.comment}*/
  </#if>
  <#if (field.lower=='dr')>
        ${field.lower}: 0,/*${field.comment}*/
  </#if>
</#list>
      };
    },
    /**${cnName}-列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form };
      this.rq.post("${serverName}/${className}/list", param).then(res => {
        if (res.code == 200) {
          this.dataList = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
	  this.loading = false;	  
    },
    /**${cnName}-删除*/
    doDelete(ids) {
      this.$confirm("你确定要删除吗?", "提示", { confirmButtonText: "确定", cancelButtonText: "取消", type: "warning" }).then(() => {
        this.rq.post("${serverName}/${className}/delete", ids).then(res => {
          if (res.code == 200) {
            this.$message.success("删除成功");
            this.loadData();
          } else this.$message.error(res.msg);
        });
      }).catch(() => {});
    },
  },
};
</script>
<style scoped lang="scss">
@import "@/styles/minxi";
</style>
