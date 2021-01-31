<?xml version="1.0" ?>
<xsl:stylesheet
 version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns="http://www.w3.org/1999/xhtml">
 <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
 <xsl:template match="/audit">
	 <html>
	 
	 <head>
		<title>Question 3</title>
	 </head>
	 
	 <body>
		<h1>
			<xsl:text>Enrolment Statistics</xsl:text>
		</h1>
		
		<p>
			<b><xsl:text>Campus: </xsl:text></b>
			<xsl:value-of select="@campus" />
		<br/>
			<b><xsl:text>Year: </xsl:text></b>
			<xsl:value-of select="@year" />
		<br/>
			<b><xsl:text>Session: </xsl:text></b>
			<xsl:value-of select="@session" />
		</p>
		
		<table border="1" style="border-collapse:seperate;">
			<tr>
				<th><xsl:text>ID</xsl:text></th>
				<th><xsl:text>Subject</xsl:text></th>
				<th><xsl:text>Enrol</xsl:text></th>
				<th><xsl:text>Withdrawn</xsl:text></th>
			</tr>
			
			<xsl:for-each select="subject">
				<tr>
					<td class="info">
						<xsl:value-of select="@sid" />
					</td>
					<td class="info">
						<xsl:value-of select="code" />
						<xsl:text>: </xsl:text>
						<xsl:value-of select="title" />
					</td>
					<td class="info">
						<xsl:value-of select="statistics/enrol" />
					</td>
					<td class="info">
						<xsl:value-of select="statistics/withdrawn" />
					</td>
				</tr>
			</xsl:for-each>
		</table>
		
	 </body>
	 
	 </html>
 </xsl:template>
</xsl:stylesheet>