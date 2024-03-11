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

	<xsl:template match="person">
		<xsl:if test="count(lastName) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.lastName')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="lastName" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(firstName) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.firstName')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="firstName" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(middleName) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.middleName')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="middleName" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(multipleUnit) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.multipleUnit')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="multipleUnit" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(streetAddress) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.streetAddress')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="streetAddress" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(city) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.city')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="city" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(state) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.state')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="state" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(zipCode) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.zipCode')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="zipCode" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(country) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.country')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="country" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(workPhone) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.workPhone')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="workPhone" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(homePhone) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.homePhone')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="homePhone" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(cellPhone) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.cellPhone')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="cellPhone" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(fax) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.fax')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="fax" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(email) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'person.email')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="email" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

	</xsl:template>
</xsl:stylesheet>
