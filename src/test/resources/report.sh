#!/bin/bash
sesAccess='sonaimuthu.alagarsamy@qualitestgroup.com'
sesSecret='hlwsgvnwgrwpkqlr'
sesFromName="Sonaimuthu Alagarsamy"
sesFromAddress="sonaimuthu.alagarsamy@qualitestgroup.com"
sesToAddress="$2"
sesSubject="Subject:Qualiframe automation report"
sesSMTP="smtp.office365.com"
sesPort="587"
sesMessage=$'The attached is the automation test execution summary report. To view full execution results please visit - https://gitlab.com/qualitest.group/general/assets/java/qualiframe/qualiframev2/-/pipelines. This is an auto-generated email from Gitlab pipeline with the test results attached'
sesFile="$1"
sesMIMEType=`file --mime-type "$sesFile" | sed 's/.*: //'`

mails=$(echo $sesToAddress | tr ";" "\n")
a='--mail-rcpt'
c=''
for addr in $mails
do
    b="$addr"
    c="${c} ${a} ${b}"
done
echo ${c}
curl -v --url smtp://$sesSMTP:$sesPort --ssl-reqd  --mail-from $sesFromAddress ${c} --user $sesAccess:$sesSecret -F '=(;type=multipart/mixed' -F "=$sesMessage;type=application/zip" -F "file=@$sesFile;type=$sesMIMEType" -F '=)' -H "Subject: $sesSubject" -H "From: $sesFromName <$sesFromAddress>" -H "To: <$sesToAddress>"