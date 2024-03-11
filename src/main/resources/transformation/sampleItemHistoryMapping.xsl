<?xml version="1.0"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
	xmlns:java="http://www.oracle.com/XSL/Transform/java">
	<xsl:output method="html" indent="yes" />
	<xsl:variable name="resources"
		select="java:util.ResourceBundle.getBundle('languages/message')" />

	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="sampleItem">
		<xsl:if test="count(sortOrder) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sampleitem.sortOrder')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="sortOrder" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(sourceOfSampleId) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'resultsentry.sampleSource')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="sourceOfSampleId" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(typeOfSampleId) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'resultsentry.sampleType')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="typeOfSampleId" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(sourceOther) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'resultsentry.sourceOther')" />
				</td>
				<td>
					<i>
						<xsl:value-of select="sourceOther" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(quantity) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sampleitem.quantity')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="quantity" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
