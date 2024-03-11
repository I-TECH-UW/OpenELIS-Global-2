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

	<xsl:template match="sample">
		<xsl:if test="count(accessionNumber) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.accessionNumber')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="accessionNumber" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(collectionDateForDisplay)!= 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.collectionDate')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="collectionDateForDisplay" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(collectionTimeForDisplay)!= 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.collectionTime')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="collectionTimeForDisplay" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(clientReference) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.clientReference')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="clientReference" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(domain) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.domain')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="domain" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(packageId) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.packageId')" />
				</td>
				<td>
					<i>
						<xsl:value-of select="packageId" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(revision) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.revision')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="revision" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(enteredDateForDisplay) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.enteredDateForDisplay')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="enteredDateForDisplay" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(receivedDateForDisplay) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.receivedDateForDisplay')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="receivedDateForDisplay" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(releasedDateForDisplay) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.releasedDateForDisplay')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="releasedDateForDisplay" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<!--dont display status changes -->
		<!--xsl:if test="count(status) != 0"> </xsl:if -->
		<xsl:if test="count(stickerReceivedFlag) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.stickerReceivedFlag')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="stickerReceivedFlag" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(sysUserId) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.sysUserId')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="sysUserId" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(transmissionDate) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.transmissionDate')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="transmissionDate" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(referredCultureFlag) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'sample.referredCultureFlag')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="referredCultureFlag" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
