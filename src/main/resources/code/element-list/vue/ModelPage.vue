/*${cnName}分页列表,作者:${author},日期:${time}*/
<template>
  <el-card>
    <el-form :inline="true" label-suffix=":" label-width="120px" @keyup.enter.native="loadData"  class="form-find">
      <el-row>
<#list fields as field>
  <#if (field_index < 5  && field.fillType != 0)>
      <#if field.fillType == 3>
          <el-form-item label="${field.comment}"<#if columns == true> v-show="condition.${field.lower}.show"</#if>>
            <v-select placeholder="请选${field.comment}" v-model="form.${field.lower}" dictKey="${field.dictKey}" :addBlank="true"></v-select>
          </el-form-item>
      <#elseif field.fillType == 4>
          <el-form-item label="${field.comment}从"<#if columns == true> v-show="condition.${field.lower}Start.show"</#if>>
            <el-date-picker size="mini" placeholder="请选${field.comment}开始" v-model="form.${field.lower}Start" type="datetime" value-format="yyyy-MM-ddTHH:mm:ss" style="width: 160px !important;"></el-date-picker>
          </el-form-item>
          <el-form-item label="到${field.comment}"<#if columns == true> v-show="condition.${field.lower}End.show"</#if>>
            <el-date-picker size="mini" placeholder="请选${field.comment}结束" v-model="form.${field.lower}End" type="datetime" value-format="yyyy-MM-ddTHH:mm:ss" style="width: 160px !important;"></el-date-picker>
          </el-form-item>
      <#else>
          <el-form-item label="${field.comment}"<#if columns == true> v-show="condition.${field.lower}.show"</#if>>
            <el-input size="mini" placeholder="请输入${field.comment}" v-model="form.${field.lower}"></el-input>
          </el-form-item>
      </#if>            
  </#if>
</#list>
		<el-form-item style="padding-left: 40px;">	
          <el-button size="mini" @click="loadDataInit" type="primary" plain>查询</el-button>
          <el-button size="mini" @click="doReset" type="primary" plain>重置</el-button>
          <el-button size="mini" @click="doDelete(selectIds)" v-if="selectIds.length > 0" type="danger">删除</el-button>
  <#if columns == true>
          <v-columns :condition="condition" :column="column" component="${ClassName}Page"></v-columns>
  </#if>
          <el-button size="mini" @click="() => allShow = !allShow" type="text">{{ allShow ? "折叠" : "展开" }}</el-button>
	    </el-form-item>		  
      </el-row>
      <el-row v-if="allShow">
<#list fields as field>
  <#if (field_index >= 5  && field.fillType != 0)>
      <#if field.fillType == 3>
          <el-form-item label="${field.comment}"<#if columns == true> v-show="condition.${field.lower}.show"</#if>>
            <v-select placeholder="请选${field.comment}" v-model="form.${field.lower}" dictKey="${field.dictKey}" :addBlank="true"></v-select>
          </el-form-item>
      <#elseif field.fillType == 4>
          <el-form-item label="${field.comment}从"<#if columns == true> v-show="condition.${field.lower}Start.show"</#if>>
            <el-date-picker size="mini" placeholder="请选${field.comment}开始" v-model="form.${field.lower}Start" type="datetime" value-format="yyyy-MM-ddTHH:mm:ss" style="width: 160px !important;"></el-date-picker>
          </el-form-item>
          <el-form-item label="到${field.comment}"<#if columns == true> v-show="condition.${field.lower}End.show"</#if>>
            <el-date-picker size="mini" placeholder="请选${field.comment}结束" v-model="form.${field.lower}End" type="datetime" value-format="yyyy-MM-ddTHH:mm:ss" style="width: 160px !important;"></el-date-picker>
          </el-form-item>
      <#else>
          <el-form-item label="${field.comment}"<#if columns == true> v-show="condition.${field.lower}.show"</#if>>
            <el-input size="mini" placeholder="请输入${field.comment}" v-model="form.${field.lower}"></el-input>
          </el-form-item>
      </#if>  
  </#if>
</#list>
      </el-row>
    </el-form>
	<div class="table-operator">
	  <el-button size="mini" @click="doAdd" type="primary">添加</el-button>
	  <#if excel == 1>
      <el-button size="mini" @click="doExport" type="primary">导出</el-button>
      <v-upload :doImport="doImport" label="导入"></v-upload>
	  </#if>
	</div>
    <v-table :data="dataList" @selection-change="(rows) => selectChange(rows, '${idName}')" :loading="loading" height="calc(100vh - 400px)">
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
      <el-table-column label="操作" width="300" fixed="right" align="center"<#if columns == true> v-if="column.operate.show"</#if> >
        <template slot-scope="props">
          <el-button type="text" size="mini" @click="doEdit(props.row)">编辑</el-button><el-divider direction="vertical"></el-divider>
          <el-button type="text" size="mini" @click="doDelete([props.row.${idName}])" style="color: #F56C6C;">删除</el-button><el-divider direction="vertical"></el-divider>
		  <el-dropdown>
		    <el-button type="text" size="mini">
		      更多
		    </el-button>
		    <el-dropdown-menu slot="dropdown">
		      <el-dropdown-item>
				<el-button type="text" size="mini" @click="doDelete([props.row.${idName}])">删除</el-button>
		      </el-dropdown-item>
		    </el-dropdown-menu>
		  </el-dropdown>		  
        </template>
      </el-table-column>
    </v-table>
    <v-page :page="page" :total="total" :size="size" :pageChange="pageChange" :sizeChange="sizeChange"></v-page>
    <${ClassName}Dialog ref="dialog" :loadData="loadData"></${ClassName}Dialog>
  </el-card>
</template>
<script>
import ${ClassName}Dialog from './${ClassName}Dialog';
import { pageMix } from "@/common/page";
export default {
  mixins: [pageMix],
  components: { ${ClassName}Dialog },
  data() {
    return {
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
    <#if field.fillType == 4>
        ${field.lower}Start: { show: true, text: "${field.comment}开始" },
        ${field.lower}End: { show: true, text: "${field.comment}结束" },
    <#else>
        ${field.lower}: { show: true, text: "${field.comment}" },
    </#if> 
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
    /**${cnName}-导出Excel*/
    doExport() {
      const param = { ...this.form };
      this.rq.post("${serverName}/${className}/exportExcel", param, { responseType: "blob" }).then(res => {
        this.downloadFile(res, "${cnName}.xlsx")
      });
    },
    /**${cnName}-导入Excel*/
    doImport(obj) {
      this.rq.post("${serverName}/${className}/importExcel", obj.file).then(res => {
        if (res.code == 200)
          this.loadData();
        else
          this.$message.error(res.msg);
      });
    },
</#if>
    /**${cnName}-查询参数*/
    initForm() {
      return {
<#list fields as field>
  <#if (field.fillType != 0)> 
    <#if field.fillType == 4>
        ${field.lower}Start: null,/*${field.comment}开始*/
        ${field.lower}End: null,/*${field.comment}结束*/
    <#else>
        ${field.lower}: null,/*${field.comment}*/
    </#if>         
  </#if>
  <#if (field.lower=='dr')>
        ${field.lower}: 0,/*${field.comment}*/
  </#if>	    
</#list>
      };
    },
    /**${cnName}-分页列表*/
    loadData() {
      this.loading = true;
      const param = { ...this.form, page: this.page, size: this.size };
      this.rq.post("${serverName}/${className}/page", param).then(res => {
        if (res.code == 200) {
          this.dataList = res.data.dataList;
          this.total = res.data.rowCount;
        } else this.$message.error(res.msg);
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
      }).catch(() => { });
    },
  },
};
</script>
<style scoped lang="scss">
@import "@/styles/minxi";
</style>
