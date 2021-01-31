<?xml version="1.0" ?>
<xsl:stylesheet
 version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns="http://www.w3.org/1999/xhtml">
 <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
 <xsl:template match="/result">
	 <html>
	 <head>
		<title>Question 2</title>
		<style>
		.name{
			font-size:8px;
			text-align:right;
			color:grey;
			padding:5px;
			border-style:dashed;
			}
		.info{
			color:maroon;
			padding:5px;
			border-style:dashed;
			}
		</style>
	 </head>
	 <body>
		<h2>
			<xsl:text>Exam Result</xsl:text>
		</h2>
		<table border="1" style="border-collapse:seperate;">
			<tr>
				<td class="name">
					<xsl:text>Reference number</xsl:text>
				</td>
				<td class="info">
					<xsl:value-of select="@ref" />
				</td>
			</tr>
			<tr>
				<td class="name">
					<xsl:text>Exam number</xsl:text>
				</td>
				<td class="info">
					<xsl:value-of select="examId" />
				</td>
			</tr>
			<tr>
				<td class="name">
					<xsl:text>Contestant number</xsl:text>
				</td>
				<td class="info">
					<xsl:value-of select="contestantId" />
				</td>
			</tr>
			<tr>
				<td class="name">
					<xsl:text>Digital Signature</xsl:text>
				</td>
				<td class="info">
					<xsl:value-of select="digitalSignature" />
				</td>
			</tr>
			<tr>
				<td class="name">
					<xsl:text>Score</xsl:text>
				</td>
				<td class="info">
					<xsl:value-of select="score" />
				</td>
			</tr>
			<tr>
				<td class="name">
					<xsl:text>Band</xsl:text>
				</td>
				<td class="info">
					<xsl:value-of select="band" />
				</td>
			</tr>
		</table>	
	 </body>
	 </html>
 </xsl:template>
</xsl:stylesheet>