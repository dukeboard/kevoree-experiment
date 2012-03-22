package org.kevoree.experiment.modelScript


/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 04/08/11
 * Time: 11:51
 */

object Runner extends App {
  if (args.contains("1")) {
    sendNotification.RunCompleteExperimentation.main(args)
  } else if (args.contains("2")) {
    failureManagement.RunCompleteExperimentation.main(args)
  } else if (args.contains("3")) {
    recoverOnFailure.RunCompleteExperimentation.main(args)
  }

}