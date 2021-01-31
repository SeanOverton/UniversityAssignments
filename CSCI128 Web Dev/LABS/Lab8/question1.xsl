<?xml version="1.0" ?>
<xsl:stylesheet
 version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns="http://www.w3.org/1999/xhtml">
 <xsl:output method="xml" indent="yes" encoding="UTF-8"/>
 <xsl:template match="/result">
	 <html>
	 <head>
		<title>Question 1</title>
	 </head>
	 <body>
		<h1>
			<xsl:text>Exam Result</xsl:text>
		</h1>
		<p>
			<xsl:text>Reference number: </xsl:text>
			<xsl:value-of select="@ref" />
		<br/>
			<xsl:text>Exam number: </xsl:text>
			<xsl:value-of select="examId" />
		<br/>
			<xsl:text>Contestant number: </xsl:text>
			<xsl:value-of select="contestantId" />
		<br/>
			<xsl:text>Digital Signature: </xsl:text>
			<xsl:value-of select="digitalSignature" />
		<br/>
			<xsl:text>Score: </xsl:text>
			<xsl:value-of select="score" />
		<br/>
			<xsl:text>Band: </xsl:text>
			<xsl:value-of select="band" />
		</p>
	 </body>
	 </html>
 </xsl:template>
</xsl:stylesheet>