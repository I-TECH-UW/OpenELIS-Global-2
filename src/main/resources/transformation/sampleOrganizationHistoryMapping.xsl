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

	<xsl:template match="sampleOrganization">
		<xsl:if test="count(organization) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'organization.id')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="organization" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(sample) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.id')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="sample" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
