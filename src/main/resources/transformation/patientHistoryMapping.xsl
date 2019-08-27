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

	<xsl:template match="patient">
		<xsl:if test="count(person) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.personId')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="person" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(race) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.race')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="race" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(gender) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.gender')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="gender" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(birthDateForDisplay) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.birthDate')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="birthDateForDisplay" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(birthTimeForDisplay) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.birthTime')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="birthTimeForDisplay" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(deathDateForDisplay) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.deathDate')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="deathDateForDisplay" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(epiFirstName) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.epiFirstName')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="epiFirstName" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(epiMiddleName) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.epiMiddleName')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="epiMiddleName" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count(epiLastName) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.epiLastName')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="epiLastName" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(nationalId) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.nationalId')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="nationalId" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(ethnicity) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.ethnicity')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="ethnicity" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(schoolAttend) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.schoolAttend')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="schoolAttend" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(medicareId) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.medicareId')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="medicareId" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(medicaidId) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.medicaidId')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="medicaidId" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(birthPlace) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.birthPlace')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="birthPlace" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(externalId) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.externalId')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="externalId" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>

		<xsl:if test="count(chartNumber) != 0">
			<tr>
				<td width="33%">
					<xsl:value-of
						select="java:getString($resources,'patient.chartNumber')" />
				</td>
				<td width="33%">
					<i>
						<xsl:value-of select="chartNumber" />
					</i>
				</td>
				<td width="33%">
					<xsl:text>&#160;</xsl:text>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
